package com.gilab.wjj.persistence.model;

import java.util.List;

/**
 * Created by yuankui on 1/26/18.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class AllBasicRentResult {
    private SpecialMonthBasicRentResult firstMonthBasicRentResult;
    private List<SpecialMonthBasicRentResult> extendMonthRentResults;
    private List<NormalMonthBasicRentResult> normalMonthBasicRentResults;
    private SpecialMonthBasicRentResult lastMonthBasicRentResult;
    private List<BasicLedger> basicLedgerDetails;
    private double total;

    public AllBasicRentResult(){

    }

    public AllBasicRentResult(SpecialMonthBasicRentResult firstMonthBasicRentResult, List<SpecialMonthBasicRentResult> extendMonthRentResults, List<NormalMonthBasicRentResult> normalMonthBasicRentResults, SpecialMonthBasicRentResult lastMonthBasicRentResult, List<BasicLedger> basicLedgerDetails, double total) {
        this.firstMonthBasicRentResult = firstMonthBasicRentResult;
        this.extendMonthRentResults = extendMonthRentResults;
        this.normalMonthBasicRentResults = normalMonthBasicRentResults;
        this.lastMonthBasicRentResult = lastMonthBasicRentResult;
        this.basicLedgerDetails = basicLedgerDetails;
        this.total = total;
    }

    public SpecialMonthBasicRentResult getFirstMonthBasicRentResult() {
        return firstMonthBasicRentResult;
    }

    public void setFirstMonthBasicRentResult(SpecialMonthBasicRentResult firstMonthBasicRentResult) {
        this.firstMonthBasicRentResult = firstMonthBasicRentResult;
    }

    public List<SpecialMonthBasicRentResult> getExtendMonthRentResults() {
        return extendMonthRentResults;
    }

    public void setExtendMonthRentResults(List<SpecialMonthBasicRentResult> extendMonthRentResults) {
        this.extendMonthRentResults = extendMonthRentResults;
    }

    public List<NormalMonthBasicRentResult> getNormalMonthBasicRentResults() {
        return normalMonthBasicRentResults;
    }

    public void setNormalMonthBasicRentResults(List<NormalMonthBasicRentResult> normalMonthBasicRentResults) {
        this.normalMonthBasicRentResults = normalMonthBasicRentResults;
    }

    public SpecialMonthBasicRentResult getLastMonthBasicRentResult() {
        return lastMonthBasicRentResult;
    }

    public void setLastMonthBasicRentResult(SpecialMonthBasicRentResult lastMonthBasicRentResult) {
        this.lastMonthBasicRentResult = lastMonthBasicRentResult;
    }

    public List<BasicLedger> getBasicLedgerDetails() {
        return basicLedgerDetails;
    }

    public void setBasicLedgerDetails(List<BasicLedger> basicLedgerDetails) {
        this.basicLedgerDetails = basicLedgerDetails;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "AllBasicRentResult{" +
                "firstMonthBasicRentResult=" + firstMonthBasicRentResult +
                ", extendMonthRentResults=" + extendMonthRentResults +
                ", normalMonthBasicRentResults=" + normalMonthBasicRentResults +
                ", lastMonthBasicRentResult=" + lastMonthBasicRentResult +
                ", basicLedgerDetails=" + basicLedgerDetails +
                ", total=" + total +
                '}';
    }
}

