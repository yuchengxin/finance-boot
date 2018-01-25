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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    private double tax_rate_min;
    private double tax_rate_max;
    private int tax_rate_line;

    public BasicRentManager(@Value("${tax.rate_min}") double tax_rate_min,
                           @Value("${tax.rate_max}") double tax_rate_max,
                           @Value("${tax.line}") int tax_rate_line) {
        this.tax_rate_min = tax_rate_min;
        this.tax_rate_max = tax_rate_max;
        this.tax_rate_line = tax_rate_line;
    }

    @Override
    public List<BasicLedger> calBasicRentDetail(long contractId) {
        Contract contract = contractDao.getContract(contractId);
        if(contract == null) {
            logger.error("can't find contract[d%]", contractId);
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "Cannot find contract[" + contractId + "]");
        }
        if(contract.getLeasebackPrice() == null || contract.getLeasebackPrice() <= 0
                || contract.getPaybackDate() == null || contract.getPaybackDate() <= 0
                || contract.getPayStartDate() == null || contract.getPayStartDate() <= 0
                || contract.getContractTerDate() == null || contract.getContractTerDate() <= 0
                || contract.getContractStatus() == ContractStatus.UNSTARTED){
            logger.error("can't cal contract[d%]", contractId);
            throw new FinanceRuntimeException(FinanceErrMsg.PRECONDITION_MISMATCH, "条件未达成，不允许计算[" + contract.getContractNo()+ "]");
        }
        Proposal proposal = proposalDao.getProposal(contract.getProposalId());
        if(proposal == null){
            logger.error("can't find proposal[d%]", contract.getProposalId());
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "Cannot find contract[" + contractId +"]'s proposal");
        }
        long date = contract.getPayStartDate();
        int i =0;
        List<BasicLedger> result = new ArrayList<>();
        while(date <= contract.getContractTerDate()){
            BasicLedger basicLedger = calBasicRentMonth(contract.getPayStartDate(), contract.getLeasebackPrice(), proposal, date);
            basicLedger.setContractId(contractId);
            basicLedger.setContractNo(contract.getContractNo());
            basicLedger.setBeneficiaryId(contract.getBeneficiary().getId());
            result.add(basicLedger);
            i++;
            date = DateUtils.convertJodaTime(contract.getPayStartDate()).plusMonths(i).getMillis();
        }
        return result;
    }

    @Override
    public List<BasicLedger> preCalBasicRentDetail(long paybackDate, int leasebackPrice, long proposalId) {
        Proposal proposal = proposalDao.getProposal(proposalId);
        if(proposal == null){
            logger.error("can't find proposal[d%]", proposalId);
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_RESOURCE_NOT_CAPABLE, "proposal isn't exist");
        }
        long payStartTime = DateUtils.convertJodaTime(paybackDate).plusYears(proposal.getMarketCulLife()).plusDays(1).getMillis();
        long contractTerDate = calContractTerDate(payStartTime, proposal);

        long date = payStartTime;
        int i = 0;
        List<BasicLedger> result = new ArrayList<>();
        while(date <= contractTerDate){
            result.add(calBasicRentMonth(payStartTime, leasebackPrice, proposal, date));
            i++;
            date = DateUtils.convertJodaTime(payStartTime).plusMonths(i).getMillis();
        }
        return result;
    }

    @Override
    public List<BasicRentMonthResult> monthlyRentReport(long date) {
        return null;
    }

    private BasicLedger calBasicRentMonth(long payStartTime, int leasebackPrice, Proposal proposal, long date) {
        long contractTerDate = calContractTerDate(payStartTime, proposal);
        if (!isDateLegal(payStartTime, contractTerDate, date)) {
            logger.error("date is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "date " + DateUtils.datetimeString(date) + " is illegal");
        }
        PeriodCalStandard actualPeriodStart = getPeriod(payStartTime, proposal, DateUtils.getFirstDayOfMonth(date));
        PeriodCalStandard actualPeriodEnd = getPeriod(payStartTime, proposal, DateUtils.getLastDayOfMonth(date));
        if (actualPeriodStart == null && actualPeriodEnd == null) {
            logger.error("date is illegal");
            throw new FinanceRuntimeException(FinanceErrMsg.NAMED_INPUT_ILLEGAL, "date is illegal");
        }
        //跨月份计算
        if (actualPeriodStart != null && actualPeriodEnd != null && actualPeriodStart != actualPeriodEnd) {
            double annualRentStart = calAnnualRent(leasebackPrice, actualPeriodStart);
            double annualRentEnd = calAnnualRent(leasebackPrice, actualPeriodEnd);
            int days = DateUtils.getDaysOfMonth(date);
            Date dateTime = DateUtils.convertUtilDate(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateTime);
            int month = calendar.get(Calendar.MONTH);
            int lineDaysPre = calendar.get(Calendar.DATE) - 1;
            if (month == 2) {
                days = 28;
                if (date == DateUtils.getLastDayOfMonth(date)) lineDaysPre = 28;
            }
            int lineDaysPost = days - lineDaysPre;
            double resultTaxPre = roundingNum((lineDaysPre * annualRentStart) / (12 * days) + (lineDaysPost * annualRentEnd) / (12 * days));
            double finalTaxRate = resultTaxPre <= tax_rate_line ? tax_rate_min : tax_rate_max;
            double resultTaxPost = roundingNum(resultTaxPre * (1- finalTaxRate));
            String calFormula = DateUtils.datetimeString(date, "yyyy-MM") + "月是跨界限的月份，前" + lineDaysPre + "天属于第" + actualPeriodStart.getPeriod() + "期，基本返租费率为" + actualPeriodStart.getProportion()
                    + ";后" + lineDaysPost + "天属于第" + actualPeriodEnd.getPeriod() + "期，基本返租费率为" + actualPeriodEnd.getProportion()
                    + ";税率为" + finalTaxRate;
            return new BasicLedger.Builder()
                    .planPayDate(DateUtils.getSomeDayOfMonth(DateUtils.convertJodaTime(date).plusMonths(-1).getMillis(), 28))
                    .planPayCountPre(resultTaxPre)
                    .planPayCountPost(resultTaxPost)
                    .calFormula(calFormula)
                    .payStatus(PayStatus.UNPAID)
                    .build();
        }
        PeriodCalStandard actualPeriod = actualPeriodStart == null ? actualPeriodEnd : actualPeriodStart;
        double annualRent = calAnnualRent(leasebackPrice, actualPeriod);
        //第一个月计算
        if (date == payStartTime) {
            double resultTaxPre;
            double finalTaxRate;
            double resultTaxPost;
            String calFormula;
            if(payStartTime == DateUtils.getFirstDayOfMonth(payStartTime)) {
                resultTaxPre = roundingNum(annualRent / 12);
                finalTaxRate = resultTaxPre <= tax_rate_line ? tax_rate_min : tax_rate_max;
                resultTaxPost = roundingNum(resultTaxPre * (1- finalTaxRate));
                calFormula = DateUtils.datetimeString(date, "yyyy-MM") + "月是返租第一个月，属于第" + actualPeriod.getPeriod() + "期，基本返租费率为" + actualPeriod.getProportion()
                        + ";税率为" + finalTaxRate;
            } else {
                int days = DateUtils.getDaysOfMonth(date);
                Date dateTime = DateUtils.convertUtilDate(date);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(dateTime);
                int month = calendar.get(Calendar.MONTH);
                int lineDaysPre = calendar.get(Calendar.DATE) - 1;
                if (month == 2) {
                    days = 28;
                    if (date == DateUtils.getLastDayOfMonth(date)) lineDaysPre = 28;
                }
                int lineDaysPost = days - lineDaysPre;
                resultTaxPre = roundingNum((lineDaysPost * annualRent) / (12 * days) + (annualRent / 12));
                finalTaxRate = resultTaxPre <= tax_rate_line ? tax_rate_min : tax_rate_max;
                resultTaxPost = roundingNum(resultTaxPre * (1- finalTaxRate));
                calFormula = DateUtils.datetimeString(date, "yyyy-MM") + "月是返租第一个月，但是不足一整个月，该月剩余天数" + lineDaysPost + "天并入下月一起计算，该月属于第" + actualPeriod.getPeriod() + "期，基本返租费率为" + actualPeriod.getProportion()
                        + ";税率为" + finalTaxRate;
            }
            return new BasicLedger.Builder()
                    .planPayDate(DateUtils.getSomeDayOfMonth(date, 28))
                    .planPayCountPre(resultTaxPre)
                    .planPayCountPost(resultTaxPost)
                    .calFormula(calFormula)
                    .payStatus(PayStatus.UNPAID)
                    .build();
        }
        //最后一个月
        if(date == contractTerDate){
            int days = DateUtils.getDaysOfMonth(date);
            Date dateTime = DateUtils.convertUtilDate(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dateTime);
            int month = calendar.get(Calendar.MONTH);
            int lineDaysPre = calendar.get(Calendar.DATE) - 1;
            if (month == 2) {
                days = 28;
                if (date == DateUtils.getLastDayOfMonth(date)) lineDaysPre = 28;
            }
            double  resultTaxPre = roundingNum((lineDaysPre * annualRent) / (12 * days));
            double finalTaxRate = resultTaxPre <= tax_rate_line ? tax_rate_min : tax_rate_max;
            double resultTaxPost = roundingNum(resultTaxPre * (1- finalTaxRate));
            String calFormula = DateUtils.datetimeString(date, "yyyy-MM") + "月是返租最后一个月，但不足一整个月，该月剩余天数为" + lineDaysPre + "天，该月属于第" + actualPeriod.getPeriod() + "期，基本返租费率为" + actualPeriod.getProportion()
                    + ";税率为" + finalTaxRate;
            return new BasicLedger.Builder()
                    .planPayDate(DateUtils.getSomeDayOfMonth(date, 28))
                    .planPayCountPre(resultTaxPre)
                    .planPayCountPost(resultTaxPost)
                    .calFormula(calFormula)
                    .payStatus(PayStatus.UNPAID)
                    .build();
        }

        double  resultTaxPre = roundingNum(annualRent / 12);
        double finalTaxRate = resultTaxPre <= tax_rate_line ? tax_rate_min : tax_rate_max;
        double resultTaxPost = roundingNum(resultTaxPre * (1- finalTaxRate));
        String calFormula = DateUtils.datetimeString(date, "yyyy-MM") + "月属于第" + actualPeriod.getPeriod() + "期，基本返租费率为" + actualPeriod.getProportion()
                + ";税率为" + finalTaxRate;
        return new BasicLedger.Builder()
                .planPayDate(DateUtils.getSomeDayOfMonth(date, 28))
                .planPayCountPre(resultTaxPre)
                .planPayCountPost(resultTaxPost)
                .calFormula(calFormula)
                .payStatus(PayStatus.UNPAID)
                .build();

    }

    private double roundingNum(double num){
        return new BigDecimal(num).setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private double calAnnualRent(int leasebackPrice, PeriodCalStandard period){
        return leasebackPrice * period.getProportion();
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
        return date >= payStartTime && date <= ContractTerTime;
    }

    private long calContractTerDate(long payStartTime, Proposal proposal){
        return DateUtils.convertJodaTime(payStartTime).plusYears(proposal.getLeasebackLife() - proposal.getMarketCulLife()).getMillis();
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
