package com.gilab.wjj.persistence.model;

import com.gilab.wjj.util.DateUtils;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class BasicRentMonthResult {
    private String merchantName;
    private long contractId;
    private long proposalId;
    private String calDtail;
    private long date;
    private double result;

    public BasicRentMonthResult(String merchantName, long contractId, long proposalId, String calDtail, long date, double result) {
        this.merchantName = merchantName;
        this.contractId = contractId;
        this.proposalId = proposalId;
        this.calDtail = calDtail;
        this.date = date;
        this.result = result;
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

    public String getCalDtail() {
        return calDtail;
    }

    public void setCalDtail(String calDtail) {
        this.calDtail = calDtail;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BasicRentMonthResult{" +
                "merchantName='" + merchantName + '\'' +
                ", contractId=" + contractId +
                ", proposalId=" + proposalId +
                ", calDtail='" + calDtail + '\'' +
                ", date='" + DateUtils.datetimeString(date, "yyyy-MM-dd") + '\'' +
                ", result=" + result +
                '}';
    }

    public static class Builder{
        private String merchantName;
        private long contractId;
        private long proposalId;
        private String calDtail;
        private long date;
        private double result;

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

        public Builder calDtail(String calDtail){
            this.calDtail = calDtail;
            return this;
        }

        public Builder date(long date){
            this.date = date;
            return this;
        }

        public Builder result(double result){
            this.result = result;
            return this;
        }

        public BasicRentMonthResult build(){
            return new BasicRentMonthResult(merchantName, contractId, proposalId, calDtail, date, result);
        }
    }
}
