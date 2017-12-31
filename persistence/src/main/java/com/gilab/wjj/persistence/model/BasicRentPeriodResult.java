package com.gilab.wjj.persistence.model;

import java.util.List;

/**
 * Created by yuankui on 12/23/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class BasicRentPeriodResult {
    private String merchantName;
    private long contractId;
    private long proposalId;
    private int period;
    private PeriodSumPayment periodSumPayment;
    private List<BasicResult> periodResult;
    private double periodTotal;

    public BasicRentPeriodResult(String merchantName, long contractId, long proposalId, int period,
                                 List<BasicResult> periodResult, double periodTotal, PeriodSumPayment periodSumPayment) {
        this.merchantName = merchantName;
        this.contractId = contractId;
        this.proposalId = proposalId;
        this.period = period;
        this.periodResult = periodResult;
        this.periodTotal = periodTotal;
        this.periodSumPayment = periodSumPayment;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public List<BasicResult> getPeriodResult() {
        return periodResult;
    }

    public void setPeriodResult(List<BasicResult> periodResult) {
        this.periodResult = periodResult;
    }

    public double getPeriodTotal() {
        return periodTotal;
    }

    public void setPeriodTotal(double periodTotal) {
        this.periodTotal = periodTotal;
    }

    public PeriodSumPayment getPeriodSumPayment() {
        return periodSumPayment;
    }

    public void setPeriodSumPayment(PeriodSumPayment periodSumPayment) {
        this.periodSumPayment = periodSumPayment;
    }

    @Override
    public String toString() {
        return "BasicRentPeriodResult{" +
                "merchantName='" + merchantName + '\'' +
                ", contractId=" + contractId +
                ", proposalId=" + proposalId +
                ", period=" + period +
                ", periodResult=" + periodResult +
                ", periodTotal=" + periodTotal +
                ", periodSumPayment=" + periodSumPayment +
                '}';
    }

    public static class Builder{
        private String merchantName;
        private long contractId;
        private long proposalId;
        private int period;
        private List<BasicResult> periodResult;
        private double periodTotal;
        private PeriodSumPayment periodSumPayment;

        public Builder merchantName(String merchantName){
            this.merchantName = merchantName;
            return this;
        }

        public Builder contractId(long contractId){
            this.contractId = contractId;
            return this;
        }

        public Builder proposalId(long proposalId){
            this.proposalId = proposalId;
            return this;
        }


        public Builder period(int period){
            this.period = period;
            return this;
        }

        public Builder periodResult(List<BasicResult> periodResult){
            this.periodResult = periodResult;
            return this;
        }

        public Builder periodTotal(double periodTotal){
            this.periodTotal = periodTotal;
            return this;
        }

        public Builder periodSumPayment(PeriodSumPayment periodSumPayment){
            this.periodSumPayment = periodSumPayment;
            return this;
        }

        public BasicRentPeriodResult build(){
            return new BasicRentPeriodResult(merchantName, contractId, proposalId, period, periodResult, periodTotal, periodSumPayment);
        }
    }
}
