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
    @Excel(name = "合同ID", width = 10)
    private Long contractId;
    @Excel(name = "受益人ID", width = 10)
    private Long beneficiaryId;
    @Excel(name = "合同编号", width = 10)
    private String contractNo;
    @Excel(name = "计算公式", width = 10)
    private String calFormula;
    @Excel(name = "计划支付时间", width = 10)
    private Date planPayDate;
    @Excel(name = "税前计划支付金额", width = 10)
    private Double planPayCountPre;
    @Excel(name = "税后计划支付金额", width = 10)
    private Double planPayCountPost;
    @Excel(name = "实际支付时间", width = 10)
    private Date actualPayDate;
    @Excel(name = "实际支付金额", width = 10)
    private Double actualPayCount;
    @Excel(name = "支付状态", width = 10)
    private String payStatus;

    public BasicLedgerInfo(){}

    public BasicLedgerInfo(Long id, Long contractId, Long beneficiaryId, String contractNo, String calFormula, Date planPayDate, Double planPayCountPre, Double planPayCountPost, Date actualPayDate, Double actualPayCount, String payStatus) {
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

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(Long beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    public Double getPlanPayCountPre() {
        return planPayCountPre;
    }

    public void setPlanPayCountPre(Double planPayCountPre) {
        this.planPayCountPre = planPayCountPre;
    }

    public Double getPlanPayCountPost() {
        return planPayCountPost;
    }

    public void setPlanPayCountPost(Double planPayCountPost) {
        this.planPayCountPost = planPayCountPost;
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

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "BasicLedgerInfo{" +
                "id=" + id +
                ", contractId=" + contractId +
                ", beneficiaryId=" + beneficiaryId +
                ", contractNo='" + contractNo + '\'' +
                ", calFormula='" + calFormula + '\'' +
                ", planPayDate=" + planPayDate +
                ", planPayCountPre=" + planPayCountPre +
                ", planPayCountPost=" + planPayCountPost +
                ", actualPayDate=" + actualPayDate +
                ", actualPayCount=" + actualPayCount +
                ", payStatus=" + payStatus +
                '}';
    }


}
