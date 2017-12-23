package com.gilab.wjj.persistence.model;

import java.util.List;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class BasicRentResult {
    private long contractId;
    private String merchantName;
    private long proposalId;
    private List<BasicResult> result;
    private double total;

    public BasicRentResult(long contractId, String merchantName, long proposalId, List<BasicResult> result, double total) {
        this.contractId = contractId;
        this.merchantName = merchantName;
        this.proposalId = proposalId;
        this.result = result;
        this.total = total;
    }

    public BasicRentResult(){}

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public List<BasicResult> getResult() {
        return result;
    }

    public void setResult(List<BasicResult> result) {
        this.result = result;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BasicRentResult{" +
                "contractId=" + contractId +
                ", merchantName='" + merchantName + '\'' +
                ", proposalId=" + proposalId +
                ", result=" + result +
                ", total=" + total +
                '}';
    }

    public static class Builder{
        private String merchantName;
        private long contractId;
        private long proposalId;
        private List<BasicResult> result;
        private double total;

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


        public Builder result(List<BasicResult> result){
            this.result = result;
            return this;
        }

        public Builder total(double total){
            this.total = total;
            return this;
        }

        public BasicRentResult build(){
            return new BasicRentResult(contractId, merchantName, proposalId, result, total);
        }
    }
}
