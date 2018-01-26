package com.gilab.wjj.persistence.model;

/**
 * Created by yuankui on 1/26/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class SpecialMonthBasicRentResult {
    private String calFormula;
    private long planPayDate;
    private double planPayCountPre;
    private double planPayCountPost;
    private double taxRate;
    private RentMonthMode rentMonthMode;

    public SpecialMonthBasicRentResult(String calFormula, long planPayDate, double planPayCountPre, double planPayCountPost,
                                       double taxRate, RentMonthMode rentMonthMode) {
        this.calFormula = calFormula;
        this.planPayDate = planPayDate;
        this.planPayCountPre = planPayCountPre;
        this.planPayCountPost = planPayCountPost;
        this.taxRate = taxRate;
        this.rentMonthMode = rentMonthMode;
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
        return "SpecialMonthBasicRentResult{" +
                "calFormula='" + calFormula + '\'' +
                ", planPayDate=" + planPayDate +
                ", planPayCountPre=" + planPayCountPre +
                ", planPayCountPost=" + planPayCountPost +
                ", taxRate=" + taxRate +
                ", rentMonthMode=" + rentMonthMode +
                '}';
    }

    public static class Builder{

        private long planPayDate;
        private double planPayCountPre;
        private double planPayCountPost;
        private double taxRate;
        private String calFormula;
        private RentMonthMode rentMonthMode;

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

        public Builder taxRate(double taxRate){
            this.taxRate = taxRate;
            return this;
        }

        public Builder calFormula(String calFormula){
            this.calFormula = calFormula;
            return this;
        }

        public Builder rentMonthMode(RentMonthMode rentMonthMode){
            this.rentMonthMode = rentMonthMode;
            return this;
        }

        public SpecialMonthBasicRentResult build(){
            return new SpecialMonthBasicRentResult(calFormula, planPayDate, planPayCountPre, planPayCountPost, taxRate, rentMonthMode);
        }
    }
}
