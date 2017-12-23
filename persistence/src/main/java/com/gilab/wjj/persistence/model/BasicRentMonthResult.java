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
    private BasicResult monthResult;

    public BasicRentMonthResult(String merchantName, long contractId, long proposalId, BasicResult monthResult) {
        this.merchantName = merchantName;
        this.contractId = contractId;
        this.proposalId = proposalId;
        this.monthResult = monthResult;
    }

    public BasicRentMonthResult(){}

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

    public BasicResult getMonthResult() {
        return monthResult;
    }

    public void setMonthResult(BasicResult monthResult) {
        this.monthResult = monthResult;
    }

    @Override
    public String toString() {
        return "BasicRentMonthResult{" +
                "merchantName='" + merchantName + '\'' +
                ", contractId=" + contractId +
                ", proposalId=" + proposalId +
                ", monthResult=" + monthResult +
                '}';
    }

    public static class Builder{
        private String merchantName;
        private long contractId;
        private long proposalId;
        private BasicResult monthResult;

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

        public Builder monthResult(BasicResult monthResult){
            this.monthResult = monthResult;
            return this;
        }

        public BasicRentMonthResult build(){
            return new BasicRentMonthResult(merchantName, contractId, proposalId, monthResult);
        }
    }
}
