package com.gilab.wjj.persistence.model;

import java.util.List;

/**
 * Created by yuankui on 12/23/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class BasicRentYearResult {
    private String merchantName;
    private long contractId;
    private long proposalId;
    private int year;
    private List<BasicResult> yearResult;
    private double yearTotal;

    public BasicRentYearResult(String merchantName, long contractId, long proposalId, int year, List<BasicResult> yearResult, double yearTotal) {
        this.merchantName = merchantName;
        this.contractId = contractId;
        this.proposalId = proposalId;
        this.year = year;
        this.yearResult = yearResult;
        this.yearTotal = yearTotal;
    }

    public BasicRentYearResult(){}

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<BasicResult> getYearResult() {
        return yearResult;
    }

    public void setYearResult(List<BasicResult> yearResult) {
        this.yearResult = yearResult;
    }

    public double getYearTotal() {
        return yearTotal;
    }

    public void setyearTotal(double yearTotal) {
        this.yearTotal = yearTotal;
    }

    @Override
    public String toString() {
        return "BasicRentYearResult{" +
                "merchantName='" + merchantName + '\'' +
                ", contractId=" + contractId +
                ", proposalId=" + proposalId +
                ", year=" + year +
                ", yearResult=" + yearResult +
                ", yearTotal=" + yearTotal +
                '}';
    }

    public static class Builder{
        private String merchantName;
        private long contractId;
        private long proposalId;
        private int year;
        private List<BasicResult> yearResult;
        private double yearTotal;

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


        public Builder year(int year){
            this.year = year;
            return this;
        }

        public Builder yearResult(List<BasicResult> yearResult){
            this.yearResult = yearResult;
            return this;
        }

        public Builder yearTotal(double yearTotal){
            this.yearTotal = yearTotal;
            return this;
        }

        public BasicRentYearResult build(){
            return new BasicRentYearResult(merchantName, contractId, proposalId, year, yearResult, yearTotal);
        }
    }
}
