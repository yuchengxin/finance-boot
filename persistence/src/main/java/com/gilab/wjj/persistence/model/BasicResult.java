package com.gilab.wjj.persistence.model;

import com.gilab.wjj.util.DateUtils;

/**
 * Created by yuankui on 12/23/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class BasicResult {
    private PeriodCalStandard calDtail;
    private long date;
    private double amount;

    public BasicResult(PeriodCalStandard calDtail, long date, double amount) {
        this.calDtail = calDtail;
        this.date = date;
        this.amount = amount;
    }

    public BasicResult(){}

    public PeriodCalStandard getCalDtail() {
        return calDtail;
    }

    public void setCalDtail(PeriodCalStandard calDtail) {
        this.calDtail = calDtail;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
                "calDtail='" + calDtail + '\'' +
                ", month=" + DateUtils.datetimeString(date, "yyyy-MM-dd") +
                ", amount=" + amount +
                '}';
    }

    public static class Builder{
        private PeriodCalStandard calDtail;
        private long date;
        private double amount;

        public Builder calDtail(PeriodCalStandard calDtail){
            this.calDtail = calDtail;
            return this;
        }

        public Builder date(long date){
            this.date = date;
            return this;
        }

        public Builder amount(double amount){
            this.amount = amount;
            return this;
        }

        public BasicResult build(){
            return new BasicResult(calDtail, date, amount);
        }
    }
}
