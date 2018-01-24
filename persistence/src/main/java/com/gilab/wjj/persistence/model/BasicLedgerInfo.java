package com.gilab.wjj.persistence.model;

import com.gilab.wjj.util.DateUtils;
import com.gilab.wjj.util.excel.Excel;

import java.util.Date;

/**
 * Created by che on 18/1/24.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class BasicLedgerInfo {
    @Excel(name = "id", width = 10)
    private Long id;
    @Excel(name = "合同编号", width = 10)
    private Long contractId;
    @Excel(name = "商户", width = 10)
    private Long merchantId;
    @Excel(name = "计算公式", width = 10)
    private String calFormula;
    @Excel(name = "计划支付时间", width = 10)
    private Date planPayDate;
    @Excel(name = "计划支付金额", width = 10)
    private Double planPayCount;
    @Excel(name = "实际支付时间", width = 10)
    private Date actualPayDate;
    @Excel(name = "实际支付金额", width = 10)
    private Double actualPayCount;

    public BasicLedgerInfo(){}

    public BasicLedgerInfo(Long id, Long contractId, Long merchantId, String calFormula, Date planPayDate, Double planPayCount, Date actualPayDate, Double actualPayCount) {
        this.id = id;
        this.contractId = contractId;
        this.merchantId = merchantId;
        this.calFormula = calFormula;
        this.planPayDate = planPayDate;
        this.planPayCount = planPayCount;
        this.actualPayDate = actualPayDate;
        this.actualPayCount = actualPayCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getCalFormula() {
        return calFormula;
    }

    public void setCalFormula(String calFormula) {
        this.calFormula = calFormula;
    }

    public Date getPlanPayDate() {
        return planPayDate;
    }

    public void setPlanPayDate(Date planPayDate) {
        this.planPayDate = planPayDate;
    }

    public Double getPlanPayCount() {
        return planPayCount;
    }

    public void setPlanPayCount(Double planPayCount) {
        this.planPayCount = planPayCount;
    }

    public Date getActualPayDate() {
        return actualPayDate;
    }

    public void setActualPayDate(Date actualPayDate) {
        this.actualPayDate = actualPayDate;
    }

    public Double getActualPayCount() {
        return actualPayCount;
    }

    public void setActualPayCount(Double actualPayCount) {
        this.actualPayCount = actualPayCount;
    }

    @Override
    public String toString() {
        return "BasicLedgerInfo{" +
                "id=" + id +
                ", contractId=" + contractId +
                ", merchantId=" + merchantId +
                ", calFormula='" + calFormula + '\'' +
                ", planPayDate=" + planPayDate +
                ", planPayCount=" + planPayCount +
                ", actualPayDate=" + actualPayDate +
                ", actualPayCount=" + actualPayCount +
                '}';
    }
}
