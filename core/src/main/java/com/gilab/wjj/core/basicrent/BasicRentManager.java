package com.gilab.wjj.core.basicrent;

import com.gilab.wjj.core.BasicRentAgent;
import com.gilab.wjj.exception.FinanceErrMsg;
import com.gilab.wjj.exception.FinanceRuntimeException;
import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.dao.MerchantDao;
import com.gilab.wjj.persistence.dao.ProposalDao;
import com.gilab.wjj.persistence.model.*;
import com.gilab.wjj.util.DateUtils;
import com.gilab.wjj.util.logback.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Service("basicRentAgent")
public class BasicRentManager implements BasicRentAgent {
    private static final Logger logger = LoggerFactory.getLogger(BasicRentManager.class);

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private ProposalDao proposalDao;

    @Override
    public ReqResult<BasicRentResult> calBasicRentDetail(long contractId) {
        Contract contract = contractDao.getContract(contractId);
        if(contract == null) {
            logger.error("can't find contract[d%]", contractId);
            return ReqResult.fail("Cannot find contract[%d]", contractId);
        }
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            return ReqResult.fail("Cannot find contract[%d]'s proposal", contractId);
        }
        List<BasicRentMonthResult> allResult = new ArrayList<>();
        for(int i = 1; i <= proposal.getLeasebackStages(); i++){
            allResult.addAll(calBasicRentPeriodAmount(contract, i));
        }
        BasicRentResult result = new BasicRentResult.Builder()
                .contractId(contractId)
                .merchantName(getMerchantFromContract(contract))
                .proposalId(proposal.getId())
                .result(allResult)
                .build();
        return ReqResult.success(result, "Finished calculated.");
    }

    @Override
    public ReqResult<BasicRentMonthResult> calBasicRentMonth(long contractId, long date) {
        Contract contract = contractDao.getContract(contractId);
        if(contract == null) {
            logger.error("can't find contract[d%]", contractId);
            return ReqResult.fail("Cannot find contract[%d]", contractId);
        }
        if(!isDateLegal(contract, date)){
            logger.error("date is illegal");
            return ReqResult.fail("date:{} is illegal", date);
        }
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            return ReqResult.fail("Cannot find contract[%d]'s proposal", contractId);
        }
        PeriodCalStandard period = getPeriod(contract, date);
        if(period == null){
            logger.error("date is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "date is illegal");
        }
        BasicRentMonthResult monthResult = new  BasicRentMonthResult.Builder()
                        .merchantName(getMerchantFromContract(contract))
                        .contractId(contractId)
                        .calDtail(period.toString())
                        .proposalId(contract.getProposalId())
                        .date(date)
                        .result(calBasicRentMonthAmount(contract, period, date))
                        .build();
        return ReqResult.success(monthResult, "Finished calculated month basic rent.");
    }

    @Override
    public ReqResult<BasicRentResult> calBasicRentPeriod(long contractId, int period) {
        Contract contract = contractDao.getContract(contractId);
        if(contract == null) {
            logger.error("can't find contract[d%]", contractId);
            return ReqResult.fail("Cannot find contract[%d]", contractId);
        }
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            return ReqResult.fail("Cannot find contract[%d]'s proposal", contractId);
        }
        BasicRentResult periodResult =  new BasicRentResult.Builder()
                .contractId(contractId)
                .merchantName(getMerchantFromContract(contract))
                .proposalId(proposal.getId())
                .result(calBasicRentPeriodAmount(contract, period))
                .build();
        return ReqResult.success(periodResult, "Finished calculated period basic rent.");
    }


    private List<BasicRentMonthResult>  calBasicRentPeriodAmount(Contract contract, int period) {
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
        }
        if(period > proposal.getLeasebackStages() || period <= 0){
            logger.error("period is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "Period is illegal");
        }
        PeriodCalStandard actualPeriod = null;
        List<BasicRentMonthResult> resultDetails = new ArrayList<>();
        for(PeriodCalStandard p : proposal.getConf()){
            if(p.getPeriod() == period){
                actualPeriod = p;
                break;
            }
        }
        if(actualPeriod == null){
            logger.error("period is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "Period is illegal");
        }
        long startDate = periodStartTime(contract, actualPeriod.getPeriod());
        for(int i = 0; i < actualPeriod.getDuration(); i++){
            long date = DateUtils.convertJodaTime(startDate).plusYears(i).plusMonths(1).getMillis();
            Double amount = calBasicRentMonthAmount(contract, actualPeriod, date);
            for(int j = 1; j < 12; j++){
                BasicRentMonthResult result = new BasicRentMonthResult.Builder()
                        .merchantName(getMerchantFromContract(contract))
                        .contractId(contract.getId())
                        .calDtail(actualPeriod.toString())
                        .proposalId(proposal.getId())
                        .date(DateUtils.convertJodaTime(date).plusMonths(j-1).getMillis())
                        .result(amount)
                        .build();
                resultDetails.add(result);
            }
            long yearEndTime = DateUtils.convertJodaTime(date).plusMonths(11).getMillis();
            Double yearEndAmount = calBasicRentMonthAmount(contract, actualPeriod, yearEndTime);
            BasicRentMonthResult yearEndResult = new BasicRentMonthResult.Builder()
                    .merchantName(getMerchantFromContract(contract))
                    .contractId(contract.getId())
                    .calDtail(actualPeriod.toString())
                    .proposalId(proposal.getId())
                    .date(yearEndTime)
                    .result(yearEndAmount)
                    .build();
            resultDetails.add(yearEndResult);

        }
        return resultDetails;
    }

    private Double calBasicRentMonthAmount(Contract contract, PeriodCalStandard period, long date){
        if(!isDateLegal(contract, date)){
            logger.error("date is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "date is illegal");
        }
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
        }
        Double result = null;
        double annualRent;
        annualRent = Math.rint(contract.getLeasebackPrice() * period.getProportion());
        if(isDateSameMonth(date, contract.getPayStartDate())){
            result = annualRent - Math.rint(annualRent/12)*11;
        }else
            result = Math.rint(annualRent/12);
        return result;
    }

    private PeriodCalStandard getPeriod(Contract contract, long date){
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
        }
        for(PeriodCalStandard p: proposal.getConf()){
            if(date >= periodStartTime(contract, p.getPeriod()) && date <= periodEndTime(contract, p.getPeriod())){
                return p;
            }
        }
        return null;
    }

    private String getMerchantFromContract(Contract contract){
        List<Merchant> merchants = contract.getSigner();
        String str ="";
        for (Merchant m : merchants){
            str += m.getMerchantName()+",";
        }
        return str.substring(0, str.length()-1);
    }

    private boolean isDateLegal(Contract contract, long date){
        long startTime = contract.getPayStartDate();
        long endTime = contract.getContractTerDate();
        return date >= startTime && date <= endTime;
    }

    private boolean isDateSameMonth(long dateA, long dateB){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(dateA);
        int monthA = calendar.get(Calendar.MONTH);
        calendar.setTimeInMillis(dateB);
        int monthB = calendar.get(Calendar.MONTH);
        return monthA == monthB;
    }

    private long periodStartTime(Contract contract, int period){
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
        }
        if(period > proposal.getLeasebackStages()){
            logger.error("period is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "period is illegal");
        }
        if(period == 1) return contract.getPayStartDate();
        int  year = 0;
        for(PeriodCalStandard p: proposal.getConf()){
            if(p.getPeriod() < period){
                year += p.getDuration();
            }else
                break;
        }
       return DateUtils.convertJodaTime(contract.getPayStartDate()).plusYears(year).getMillis();
    }

    private long periodEndTime (Contract contract, int period){
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
        }
        if(period > proposal.getLeasebackStages()){
            logger.error("period is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "period is illegal");
        }
        if(period == proposal.getLeasebackStages()) return contract.getContractTerDate();
        int  year = 0;
        for(PeriodCalStandard p: proposal.getConf()){
            if(p.getPeriod() <= period){
                year += p.getDuration();
            }else
                break;
        }
        return DateUtils.convertJodaTime(contract.getPayStartDate()).plusYears(year).getMillis();
    }
}
