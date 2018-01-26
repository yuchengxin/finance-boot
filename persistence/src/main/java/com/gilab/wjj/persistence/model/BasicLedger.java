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
    private long beneficiaryId;
    private String contractNo;
    private String calFormula;
    private long planPayDate;
    private double planPayCountPre;
    private double planPayCountPost;
    private Long actualPayDate;
    private double actualPayCount;
    private PayStatus payStatus;
    private double taxRate;
    private RentMonthMode rentMonthMode;

    public BasicLedger(){}

    public BasicLedger(long id, long contractId, long beneficiaryId, String contractNo, String calFormula, long planPayDate,
                       double planPayCountPre, double planPayCountPost, Long actualPayDate, double actualPayCount,
                       PayStatus payStatus, double taxRate, RentMonthMode rentMonthMode) {
        this.id = id;
        this.contractId = contractId;
        this.beneficiaryId = beneficiaryId;
        this.contractNo = contractNo;
        this.calFormula = calFormula;
        this.planPayDate = planPayDate;
        this.planPayCountPre = planPayCountPre;
        this.planPayCountPost = planPayCountPost;
        this.actualPayDate = actualPayDate;
        this.actualPayCount = actualPayCount;
        this.payStatus = payStatus;
        this.taxRate = taxRate;
        this.rentMonthMode = rentMonthMode;
    }

    @Override
    public long getId() {
        return id;
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

    public long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
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

    public double getPlanPayCountPre() {
        return planPayCountPre;
    }

    public void setPlanPayCountPre(double planPayCountPre) {
        this.planPayCountPre = planPayCountPre;
    }

    public double getPlanPayCountPost() {
        return planPayCountPost;
    }

    public void setPlanPayCountPost(double planPayCountPost) {
        this.planPayCountPost = planPayCountPost;
    }

    public Long getActualPayDate() {
        return actualPayDate;
    }

    public void setActualPayDate(Long actualPayDate) {
        this.actualPayDate = actualPayDate;
    }

    public double getActualPayCount() {
        return actualPayCount;
    }

    public void setActualPayCount(double actualPayCount) {
        this.actualPayCount = actualPayCount;
    }

    public PayStatus getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public RentMonthMode getRentMonthMode() {
        return rentMonthMode;
    }

    public void setRentMonthMode(RentMonthMode rentMonthMode) {
        this.rentMonthMode = rentMonthMode;
    }

    @Override
    public String toString() {
        return "BasicLedger{" +
                "id=" + id +
                ", contractId=" + contractId +
                ", beneficiaryId=" + beneficiaryId +
                ", contractNo='" + contractNo + '\'' +
                ", calFormula='" + calFormula + '\'' +
                ", planPayDate=" + DateUtils.datetimeString(planPayDate, "yyyy-MM-dd") +
                ", planPayCountPre=" + planPayCountPre +
                ", planPayCountPost=" + planPayCountPost +
                ", actualPayDate=" + DateUtils.datetimeString(actualPayDate, "yyyy-MM-dd") +
                ", actualPayCount=" + actualPayCount +
                ", payStatus=" + payStatus +
                ", rentMonthMode=" + rentMonthMode +
                ", taxRate=" + taxRate +
                '}';
    }

    public static class Builder{
        private long id;
        private long contractId;
        private long beneficiaryId;
        private String contractNo;
        private String calFormula;
        private long planPayDate;
        private double planPayCountPre;
        private double planPayCountPost;
        private Long actualPayDate;
        private double actualPayCount;
        private PayStatus payStatus;
        private double taxRate;
        private RentMonthMode rentMonthMode;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder contractId(long contractId){
            this.contractId = contractId;
            return this;
        }

        public Builder beneficiaryId(long beneficiaryId){
            this.beneficiaryId = beneficiaryId;
            return this;
        }

        public Builder contractNo(String contractNo){
            this.contractNo = contractNo;
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


        public Builder planPayCountPre(double planPayCountPre){
            this.planPayCountPre = planPayCountPre;
            return this;
        }

        public Builder planPayCountPost(double planPayCountPost){
            this.planPayCountPost = planPayCountPost;
            return this;
        }

        public Builder actualPayDate(Long actualPayDate){
            this.actualPayDate = actualPayDate;
            return this;
        }

        public Builder actualPayCount(double actualPayCount){
            this.actualPayCount = actualPayCount;
            return this;
        }

        public Builder payStatus(PayStatus payStatus){
            this.payStatus = payStatus;
            return this;
        }

        public Builder taxRate(double taxRate){
            this.taxRate = taxRate;
            return this;
        }

        public Builder rentMonthMode(RentMonthMode rentMonthMode){
            this.rentMonthMode = rentMonthMode;
            return this;
        }

        public BasicLedger build(){
            return new BasicLedger(id, contractId, beneficiaryId, contractNo, calFormula, planPayDate, planPayCountPre,
                    planPayCountPost, actualPayDate, actualPayCount, payStatus, taxRate, rentMonthMode);
        }
    }
}
