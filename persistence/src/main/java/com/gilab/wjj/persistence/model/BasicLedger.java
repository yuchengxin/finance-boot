package com.gilab.wjj.persistence.model;

import com.gilab.wjj.util.DateUtils;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class BasicLedger implements Entity {
    private long id;
    private long contractId;
    private long merchantId;
    private String calFormula;
    private long planPayDate;
    private double planPayCount;
    private long actualPayDate;
    private double actualPayCount;

    public BasicLedger(){}

    public BasicLedger(long id, long contractId, long merchantId, String calFormula, long planPayDate,
                       double planPayCount, long actualPayDate, double actualPayCount) {
        this.id = id;
        this.contractId = contractId;
        this.merchantId = merchantId;
        this.calFormula = calFormula;
        this.planPayDate = planPayDate;
        this.planPayCount = planPayCount;
        this.actualPayDate = actualPayDate;
        this.actualPayCount = actualPayCount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContractId() {
        return contractId;
    }

    public void setContractId(long contractId) {
        this.contractId = contractId;
    }

    public long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(long merchantId) {
        this.merchantId = merchantId;
    }

    public String getCalFormula() {
        return calFormula;
    }

    public void setCalFormula(String calFormula) {
        this.calFormula = calFormula;
    }

    public long getPlanPayDate() {
        return planPayDate;
    }

    public void setPlanPayDate(long planPayDate) {
        this.planPayDate = planPayDate;
    }

    public double getPlanPayCount() {
        return planPayCount;
    }

    public void setPlanPayCount(double planPayCount) {
        this.planPayCount = planPayCount;
    }

    public long getActualPayDate() {
        return actualPayDate;
    }

    public void setActualPayDate(long actualPayDate) {
        this.actualPayDate = actualPayDate;
    }

    public double getActualPayCount() {
        return actualPayCount;
    }

    public void setActualPayCount(double actualPayCount) {
        this.actualPayCount = actualPayCount;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BasicLedger{" +
                "id=" + id +
                ", contractId=" + contractId +
                ", merchantId=" + merchantId +
                ", calFormula='" + calFormula + '\'' +
                ", planPayDate=" + DateUtils.datetimeString(planPayDate, "yyyy-MM-dd") +
                ", planPayCount=" + planPayCount +
                ", actualPayDate=" + DateUtils.datetimeString(actualPayDate, "yyyy-MM-dd") +
                ", actualPayCount=" + actualPayCount +
                '}';
    }

    public static class Builder{
        private long id;
        private long contractId;
        private long merchantId;
        private String calFormula;
        private long planPayDate;
        private double planPayCount;
        private long actualPayDate;
        private double actualPayCount;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder contractId(long contractId){
            this.contractId = contractId;
            return this;
        }

        public Builder merchantId(long merchantId){
            this.merchantId = merchantId;
            return this;
        }

        public Builder calFormula(String calFormula){
            this.calFormula = calFormula;
            return this;
        }

        public Builder planPayDate(long planPayDate){
            this.planPayDate = planPayDate;
            return this;
        }


        public Builder planPayCount(double planPayCount){
            this.planPayCount = planPayCount;
            return this;
        }

        public Builder actualPayDate(long actualPayDate){
            this.actualPayDate = actualPayDate;
            return this;
        }

        public Builder actualPayCount(double actualPayCount){
            this.actualPayCount = actualPayCount;
            return this;
        }

        public BasicLedger build(){
            return new BasicLedger(id, contractId, merchantId, calFormula, planPayDate, planPayCount, actualPayDate, actualPayCount);
        }
    }
}
