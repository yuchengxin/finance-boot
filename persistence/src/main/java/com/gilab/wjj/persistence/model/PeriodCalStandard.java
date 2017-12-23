package com.gilab.wjj.persistence.model;

/**
 * Created by yuankui on 12/21/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class PeriodCalStandard {
    private int period;
    private int duration;
    private double proportion;

    public PeriodCalStandard(int period, int duration, double proportion) {
        this.period = period;
        this.duration = duration;
        this.proportion = proportion;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getProportion() {
        return proportion;
    }

    public void setProportion(double proportion) {
        this.proportion = proportion;
    }

    @Override
    public String toString() {
        return "{" +
                "period=" + period +
                ", duration=" + duration +
                ", proportion=" + proportion +
                '}';
    }
}
