package com.gilab.wjj.persistence.model;

/**
 * Created by yuankui on 1/26/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class NormalMonthBasicRentResult {
    private String calFormula;
    private double planPayCountPre;
    private double planPayCountPost;
    private double taxRate;
    private long startTime;
    private long endTime;

    public NormalMonthBasicRentResult(String calFormula, double planPayCountPre, double planPayCountPost, double taxRate, long startTime, long endTime) {
        this.calFormula = calFormula;
        this.planPayCountPre = planPayCountPre;
        this.planPayCountPost = planPayCountPost;
        this.taxRate = taxRate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getCalFormula() {
        return calFormula;
    }

    public void setCalFormula(String calFormula) {
        this.calFormula = calFormula;
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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "NormalMonthBasicRentResult{" +
                "calFormula='" + calFormula + '\'' +
                ", planPayCountPre=" + planPayCountPre +
                ", planPayCountPost=" + planPayCountPost +
                ", taxRate=" + taxRate +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public static class Builder{

        private double planPayCountPre;
        private double planPayCountPost;
        private double taxRate;
        private String calFormula;
        private long startTime;
        private long endTime;

        public Builder startTime(long startTime){
            this.startTime = startTime;
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

        public Builder endTime(long endTime){
            this.endTime = endTime;
            return this;
        }

        public NormalMonthBasicRentResult build(){
            return new NormalMonthBasicRentResult(calFormula, planPayCountPre, planPayCountPost, taxRate, startTime, endTime);
        }
    }


}
