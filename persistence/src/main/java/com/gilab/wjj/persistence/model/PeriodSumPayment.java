package com.gilab.wjj.persistence.model;

import org.joda.time.Period;

/**
 * Created by yuankui on 12/28/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class PeriodSumPayment {
    private PeriodCalStandard periodInfo;
    private long rentStartPayTime;
    private long rentEndPayTime;
    private int months;
    private double elevenMonthAmount;
    private double lastMonthAmount;
    private double periodTotal;

    public PeriodSumPayment(PeriodCalStandard periodInfo, long rentStartPayTime, long rentEndPayTime, double elevenMonthAmount, double lastMonthAmount, double periodTotal) {
        this.periodInfo = periodInfo;
        this.rentStartPayTime = rentStartPayTime;
        this.rentEndPayTime = rentEndPayTime;
        this.months = 12*periodInfo.getDuration();
        this.elevenMonthAmount = elevenMonthAmount;
        this.lastMonthAmount = lastMonthAmount;
        this.periodTotal = periodTotal;
    }

    public PeriodCalStandard getPeriodInfo() {
        return periodInfo;
    }

    public void setPeriodInfo(PeriodCalStandard periodInfo) {
        this.periodInfo = periodInfo;
    }

    public long getRentStartPayTime() {
        return rentStartPayTime;
    }

    public void setRentStartPayTime(long rentStartPayTime) {
        this.rentStartPayTime = rentStartPayTime;
    }

    public long getRentEndPayTime() {
        return rentEndPayTime;
    }

    public void setRentEndPayTime(long rentEndPayTime) {
        this.rentEndPayTime = rentEndPayTime;
    }

    public double getElevenMonthAmount() {
        return elevenMonthAmount;
    }

    public void setElevenMonthAmount(double elevenMonthAmount) {
        this.elevenMonthAmount = elevenMonthAmount;
    }

    public double getLastMonthAmount() {
        return lastMonthAmount;
    }

    public void setLastMonthAmount(double lastMonthAmount) {
        this.lastMonthAmount = lastMonthAmount;
    }

    public double getPeriodTotal() {
        return periodTotal;
    }

    public void setPeriodTotal(double periodTotal) {
        this.periodTotal = periodTotal;
    }

    public int getMonths() {
        return months;
    }

    @Override
    public String toString() {
        return "PeriodSumPayment{" +
                "periodInfo=" + periodInfo +
                ", rentStartPayTime=" + rentStartPayTime +
                ", rentEndPayTime=" + rentEndPayTime +
                ", months=" + months +
                ", elevenMonthAmount=" + elevenMonthAmount +
                ", lastMonthAmount=" + lastMonthAmount +
                ", periodTotal=" + periodTotal +
                '}';
    }

    public static class Builder{
        private PeriodCalStandard periodInfo;
        private long rentStartPayTime;
        private long rentEndPayTime;
        private double elevenMonthAmount;
        private double lastMonthAmount;
        private double periodTotal;

        public Builder period(PeriodCalStandard periodInfo){
            this.periodInfo = periodInfo;
            return this;
        }

        public Builder rentStartPayTime(long rentStartPayTime){
            this.rentStartPayTime = rentStartPayTime;
            return this;
        }

        public Builder rentEndPayTime(long rentEndPayTime){
            this.rentEndPayTime = rentEndPayTime;
            return this;
        }

        public Builder elevenMonthAmount(double elevenMonthAmount){
            this.elevenMonthAmount = elevenMonthAmount;
            return this;
        }

        public Builder lastMonthAmount(double lastMonthAmount){
            this.lastMonthAmount = lastMonthAmount;
            return this;
        }

        public Builder periodTotal(double periodTotal){
            this.periodTotal = periodTotal;
            return this;
        }

        public PeriodSumPayment build(){
            return new PeriodSumPayment(periodInfo, rentStartPayTime, rentEndPayTime, elevenMonthAmount, lastMonthAmount, periodTotal);
        }

    }
}
