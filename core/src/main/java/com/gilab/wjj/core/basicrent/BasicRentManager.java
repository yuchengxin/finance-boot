package com.gilab.wjj.core.basicrent;

import com.gilab.wjj.core.BasicRentAgent;
import com.gilab.wjj.exception.FinanceErrMsg;
import com.gilab.wjj.exception.FinanceRuntimeException;
import com.gilab.wjj.persistence.dao.ContractDao;
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
        double total = 0;
        List<BasicResult> allResult = new ArrayList<>();
        List<PeriodSumPayment> periodSumPayments = new ArrayList<>();
        for(int i = 1; i <= proposal.getLeasebackStages(); i++){
            List<BasicResult> periodResultAmount = calBasicRentPeriodAmount(contract.getPayStartDate(), contract.getLeasebackPrice(), proposal, i);
            allResult.addAll(periodResultAmount);
            PeriodCalStandard p = proposal.getPeriodInfo(i);
            double periodTotal =  calAnnualRent(contract.getLeasebackPrice(), p) * p.getDuration();
            total += periodTotal;
            PeriodSumPayment periodSumPayment = new PeriodSumPayment.Builder()
                    .period(p)
                    .rentStartPayTime(periodStartTime(contract.getPayStartDate(), proposal, p.getPeriod()))
                    .rentEndPayTime(periodEndTime(contract.getPayStartDate(), proposal, p.getPeriod()))
                    .elevenMonthAmount(periodResultAmount.get(0).getAmount())
                    .lastMonthAmount(periodResultAmount.get(11).getAmount())
                    .periodTotal(periodTotal)
                    .build();
            periodSumPayments.add(periodSumPayment);
        }
        BasicRentResult result = new BasicRentResult.Builder()
                .contractId(contractId)
                .merchantName(getMerchantFromContract(contract))
                .proposalId(proposal.getId())
                .periodSumPayments(periodSumPayments)
                .result(allResult)
                .total(total)
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
        date = formatDate(contract, date);
        if(!isDateLegal(contract.getPayStartDate(), contract.getContractTerDate(), date)){
            logger.error("date is illegal");
            return ReqResult.fail("date:{%d} is illegal", date);
        }
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            return ReqResult.fail("Cannot find contract[%d]'s proposal", contractId);
        }
        PeriodCalStandard period = getPeriod(contract.getPayStartDate(), proposal, date);
        if(period == null){
            logger.error("date is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "date is illegal");
        }
        BasicResult basic = new BasicResult.Builder()
                .calDtail(period)
                .date(date)
                .amount(calBasicRentMonthAmount(contract.getPayStartDate(), contract.getLeasebackPrice(), proposal, date))
                .build();
        BasicRentMonthResult monthResult = new BasicRentMonthResult.Builder()
                        .merchantName(getMerchantFromContract(contract))
                        .contractId(contractId)
                        .proposalId(contract.getProposalId())
                        .monthResult(basic)
                        .build();
        return ReqResult.success(monthResult, "Finished calculated month basic rent.");
    }

    @Override
    public ReqResult<BasicRentYearResult> calBasicRentYear(long contractId, int year) {
        Contract contract = contractDao.getContract(contractId);
        if(contract == null) {
            logger.error("can't find contract[d%]", contractId);
            return ReqResult.fail("Cannot find contract[%d]", contractId);
        }
        Calendar calendar=Calendar.getInstance();
        long startTime = contract.getPayStartDate();
        calendar.setTimeInMillis(startTime);
        int startYear = calendar.get(Calendar.YEAR);
        int everyMonth = calendar.get(Calendar.MONTH) + 1;
        long endTime = contract.getContractTerDate();
        calendar.setTimeInMillis(endTime);
        int endYear = calendar.get(Calendar.YEAR);
        if(year < startYear || year > endYear){
            logger.error("date is illegal");
            return ReqResult.fail("date:{} is illegal", year);
        }
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            return ReqResult.fail("Cannot find contract[%d]'s proposal", contractId);
        }
        int start = 0;
        int end = 12;
        if(year == startYear){
            start = everyMonth;
        }else if(year == endYear){
            end = everyMonth;
        }
        List<BasicResult> yearResult = new ArrayList<>();
        double yearTotal = 0;
        for(int i = start; i < end; i++){
            long date = DateUtils.convertJodaTime(startTime).plusYears(year - startYear).plusMonths(i + 1 - everyMonth).getMillis();
            PeriodCalStandard period = getPeriod(contract.getPayStartDate(), proposal, date);
            if(period == null){
                logger.error("period is illegal");
                throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "period is illegal");
            }
            double amount = calBasicRentMonthAmount(contract.getPayStartDate(), contract.getLeasebackPrice(), proposal, date);
            yearTotal += amount;
            BasicResult basic = new BasicResult.Builder()
                    .calDtail(period)
                    .date(date)
                    .amount(amount)
                    .build();
            yearResult.add(basic);
        }
        BasicRentYearResult basicRentYearResult = new BasicRentYearResult.Builder()
                .contractId(contractId)
                .merchantName(getMerchantFromContract(contract))
                .proposalId(proposal.getId())
                .year(year)
                .yearResult(yearResult)
                .yearTotal(yearTotal)
                .build();
        return ReqResult.success(basicRentYearResult, "Finished calculated year basic rent.");
    }

    @Override
    public ReqResult<BasicRentPeriodResult> calBasicRentPeriod(long contractId, int period) {
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
        List<BasicResult> periodResultAmount = calBasicRentPeriodAmount(contract.getPayStartDate(), contract.getLeasebackPrice(), proposal, period);
        double periodTotal = calAnnualRent(contract.getLeasebackPrice(), proposal.getPeriodInfo(period)) * proposal.getPeriodInfo(period).getDuration();
        PeriodSumPayment periodSumPayment = new PeriodSumPayment.Builder()
                .period(proposal.getPeriodInfo(period))
                .rentStartPayTime(periodStartTime(contract.getPayStartDate(), proposal, period))
                .rentEndPayTime(periodEndTime(contract.getPayStartDate(), proposal, period))
                .elevenMonthAmount(periodResultAmount.get(0).getAmount())
                .lastMonthAmount(periodResultAmount.get(11).getAmount())
                .periodTotal(periodTotal)
                .build();
        BasicRentPeriodResult periodResult =  new BasicRentPeriodResult.Builder()
                .contractId(contractId)
                .merchantName(getMerchantFromContract(contract))
                .period(period)
                .proposalId(proposal.getId())
                .periodSumPayment(periodSumPayment)
                .periodResult(periodResultAmount)
                .periodTotal(periodTotal)
                .build();
        return ReqResult.success(periodResult, "Finished calculated period basic rent.");
    }

    @Override
    public ReqResult<BasicRentResult> preCalBasicRentDetail(long paybackDate, int leasebackPrice, long proposalId) {
        Proposal proposal = proposalDao.getProposal(proposalId);
        if(proposal == null){
            logger.error("can't find proposal[d%]", proposalId);
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
        }
        long payStartTime = DateUtils.convertJodaTime(paybackDate).plusYears(proposal.getMarketCulLife()).plusDays(-1).getMillis();

        double total = 0;
        List<BasicResult> allResult = new ArrayList<>();
        List<PeriodSumPayment> periodSumPayments = new ArrayList<>();
        for(int i = 1; i <= proposal.getLeasebackStages(); i++){
            List<BasicResult> periodResultAmount = calBasicRentPeriodAmount(payStartTime, leasebackPrice, proposal, i);
            allResult.addAll(periodResultAmount);
            PeriodCalStandard p = proposal.getPeriodInfo(i);
            double periodTotal =  calAnnualRent(leasebackPrice, p) * p.getDuration();
            total += periodTotal;
            PeriodSumPayment periodSumPayment = new PeriodSumPayment.Builder()
                    .period(p)
                    .rentStartPayTime(periodStartTime(payStartTime, proposal, p.getPeriod()))
                    .rentEndPayTime(periodEndTime(payStartTime, proposal, p.getPeriod()))
                    .elevenMonthAmount(periodResultAmount.get(0).getAmount())
                    .lastMonthAmount(periodResultAmount.get(11).getAmount())
                    .periodTotal(periodTotal)
                    .build();
            periodSumPayments.add(periodSumPayment);
        }
        BasicRentResult result = new BasicRentResult.Builder()
                .proposalId(proposal.getId())
                .periodSumPayments(periodSumPayments)
                .result(allResult)
                .total(total)
                .build();
        return ReqResult.success(result, "Finished calculated.");

    }

    private List<BasicResult>  calBasicRentPeriodAmount(long payStartTime, int leasebackPrice, Proposal proposal, int period) {
        if(period > proposal.getLeasebackStages() || period <= 0){
            logger.error("period is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "Period is illegal");
        }
        PeriodCalStandard actualPeriod = proposal.getPeriodInfo(period);
        List<BasicResult> resultDetails = new ArrayList<>();
        if(actualPeriod == null){
            logger.error("period is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "Period is illegal");
        }
        long startDate = periodStartTime(payStartTime, proposal, actualPeriod.getPeriod());
        for(int i = 0; i < actualPeriod.getDuration(); i++){
            long date = DateUtils.convertJodaTime(startDate).plusYears(i).plusMonths(1).getMillis();
            Double amount = calBasicRentMonthAmount(payStartTime, leasebackPrice, proposal, date);
            for(int j = 1; j < 12; j++){
                BasicResult result = new BasicResult.Builder()
                        .calDtail(actualPeriod)
                        .date(DateUtils.convertJodaTime(date).plusMonths(j-1).getMillis())
                        .amount(amount)
                        .build();
                resultDetails.add(result);
            }
            long yearEndTime = DateUtils.convertJodaTime(date).plusMonths(11).getMillis();
            Double yearEndAmount = calBasicRentMonthAmount(payStartTime, leasebackPrice, proposal, yearEndTime);
            BasicResult yearEndResult = new BasicResult.Builder()
                    .calDtail(actualPeriod)
                    .date(yearEndTime)
                    .amount(yearEndAmount)
                    .build();
            resultDetails.add(yearEndResult);
        }
        return resultDetails;
    }

    private Double calBasicRentMonthAmount(long payStartTime, int leasebackPrice, Proposal proposal, long date){
        if(!isDateLegal(payStartTime, calContractTerDate(payStartTime, proposal), date)){
            logger.error("date is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "date is illegal");
        }
        PeriodCalStandard actualPeriod = getPeriod(payStartTime, proposal, date);
        if(actualPeriod == null){
            logger.error("date is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "date is illegal");
        }
        Double result;
        double annualRent = calAnnualRent(leasebackPrice, actualPeriod);
        if(isDateSameMonth(date, payStartTime)){
            result = annualRent - Math.rint(annualRent/12)*11;
        }else
            result = Math.rint(annualRent/12);
        return result;
    }

    private double calAnnualRent(int leasebackPrice, PeriodCalStandard period){
        return Math.rint(leasebackPrice * period.getProportion());
    }

    private PeriodCalStandard getPeriod(long payStartTime, Proposal proposal, long date){
        for(PeriodCalStandard p: proposal.getConf()){
            if(date >= periodStartTime(payStartTime, proposal, p.getPeriod())
                    && date <= periodEndTime(payStartTime, proposal, p.getPeriod())){
                return p;
            }
        }
        return null;
    }

    private long formatDate(Contract contract, long date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int year =  calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        long startTime = contract.getPayStartDate();
        calendar.setTimeInMillis(startTime);
        int startYear = calendar.get(Calendar.YEAR);
        int startMonth = calendar.get(Calendar.MONTH);
        return DateUtils.convertJodaTime(startTime).plusYears(year - startYear).plusMonths(month - startMonth).getMillis();
    }

    private String getMerchantFromContract(Contract contract){
        List<Merchant> merchants = contract.getSigner();
        String str ="";
        for (Merchant m : merchants){
            str += (m.getMerchantName() + ",");
        }
        return str.substring(0, str.length()-1);
    }

    private boolean isDateLegal(long payStartTime, long ContractTerTime, long date){
        return date >= DateUtils.convertJodaTime(payStartTime).plusMonths(1).getMillis() && date <= ContractTerTime;
    }

    private long calContractTerDate(long payStartTime, Proposal proposal){
        return DateUtils.convertJodaTime(payStartTime).plusYears(proposal.getLeasebackLife() - proposal.getMarketCulLife()).getMillis();
    }

    private boolean isDateSameMonth(long dateA, long dateB){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(dateA);
        int monthA = calendar.get(Calendar.MONTH);
        calendar.setTimeInMillis(dateB);
        int monthB = calendar.get(Calendar.MONTH);
        return monthA == monthB;
    }

    private long periodStartTime(long payStartTime, Proposal proposal, int period){
        if(period < 0 || period > proposal.getLeasebackStages()){
            logger.error("period is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "period is illegal");
        }
        if(period == 1) return payStartTime;
        int  year = 0;
        for(PeriodCalStandard p: proposal.getConf()){
            if(p.getPeriod() < period){
                year += p.getDuration();
            }else
                break;
        }
        return DateUtils.convertJodaTime(payStartTime).plusYears(year).getMillis();
    }

    private long periodEndTime(long payStartTime, Proposal proposal, int period){
        if(period < 0 || period > proposal.getLeasebackStages()){
            logger.error("period is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "period is illegal");
        }
        int  year = 0;
        for(PeriodCalStandard p: proposal.getConf()){
            if(p.getPeriod() <= period){
                year += p.getDuration();
            }else
                break;
        }
        return DateUtils.convertJodaTime(payStartTime).plusYears(year).getMillis();
    }
}
