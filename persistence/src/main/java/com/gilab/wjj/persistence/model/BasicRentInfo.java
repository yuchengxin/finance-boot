package com.gilab.wjj.persistence.model;

import com.gilab.wjj.util.excel.Excel;

import java.util.Date;
import java.util.List;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class BasicRentInfo {
    @Excel(name = "所在区域", width = 10)
    private String region;
    @Excel(name = "合同编号", width = 10)
    private String contractNo;
    @Excel(name = "版本", width = 10)
    private String contractVersion;
    @Excel(name = "认购日期", width = 10)
    private Date subscriptionDate;
    @Excel(name = "签约日期", width = 10)
    private Date signingDate;
    @Excel(name = "楼层\\房号", width = 10)
    private String buildingInfo;
    @Excel(name = "建筑面积", width = 10)
    private Double buildingSize;
    @Excel(name = "商户姓名", width = 10)
    private String signer;
    @Excel(name = "电话", width = 10)
    private String phone;
    @Excel(name = "身份证号", width = 10)
    private String merchantIdNo;
    @Excel(name = "销售原价", width = 10)
    private Integer originalPrice;
    @Excel(name = "总价", width = 10)
    private Integer totalPrice;
    @Excel(name = "签约总价", width = 10)
    private Integer signTotalPrice;
    @Excel(name = "签约状态", width = 10)
    private String contractStatus;
    @Excel(name = "签约方式", width = 10)
    private String signingMode;
    @Excel(name = "回款时间", width = 10)
    private Date paybackDate;
    @Excel(name = "记租起始日期", width = 10)
    private Date payStartDate;
    @Excel(name = "合同期", width = 10)
    private Date contractTerDate;
    @Excel(name = "商铺返租基价", width = 10)
    private Integer leasebackPrice;
    @Excel(name = "返租溢价", width = 10)
    private Integer backPremium;
    @Excel(name = "返租受益人", width = 10)
    private String beneficiary;
    @Excel(name = "受益人身份证", width = 10)
    private String beneficiaryIdNo;
    @Excel(name = "开户行", width = 10)
    private String bankInfo;
    @Excel(name = "受益人银行账户", width = 10)
    private String bankAccount;
    @Excel(name = "通讯地址", width = 10)
    private String beneficiaryAddress;
    @Excel(name = "税率", width = 10)
    private Double tariff;
    @Excel(name = "个税金额", width = 10)
    private Integer taxAmount;

    public BasicRentInfo(String region, String contractNo, String contractVersion, Date subscriptionDate, Date signingDate,
                         String buildingInfo, Double buildingSize, String signer, String phone, String merchantIdNo,
                         Integer originalPrice, Integer totalPrice, Integer signTotalPrice, String contractStatus,
                         String signingMode, Date paybackDate, Date payStartDate, Date contractTerDate, Integer leasebackPrice,
                         Integer backPremium, String beneficiary, String beneficiaryIdNo, String bankInfo, String bankAccount,
                         String beneficiaryAddress, Double tariff, Integer taxAmount) {
        this.region = region;
        this.contractNo = contractNo;
        this.contractVersion = contractVersion;
        this.subscriptionDate = subscriptionDate;
        this.signingDate = signingDate;
        this.buildingInfo = buildingInfo;
        this.buildingSize = buildingSize;
        this.signer = signer;
        this.phone = phone;
        this.merchantIdNo = merchantIdNo;
        this.originalPrice = originalPrice;
        this.totalPrice = totalPrice;
        this.signTotalPrice = signTotalPrice;
        this.contractStatus = contractStatus;
        this.signingMode = signingMode;
        this.paybackDate = paybackDate;
        this.payStartDate = payStartDate;
        this.contractTerDate = contractTerDate;
        this.leasebackPrice = leasebackPrice;
        this.backPremium = backPremium;
        this.beneficiary = beneficiary;
        this.beneficiaryIdNo = beneficiaryIdNo;
        this.bankInfo = bankInfo;
        this.bankAccount = bankAccount;
        this.beneficiaryAddress = beneficiaryAddress;
        this.tariff = tariff;
        this.taxAmount = taxAmount;
    }

    public BasicRentInfo(){
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getContractVersion() {
        return contractVersion;
    }

    public void setContractVersion(String contractVersion) {
        this.contractVersion = contractVersion;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public Date getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Date signingDate) {
        this.signingDate = signingDate;
    }

    public String getBuildingInfo() {
        return buildingInfo;
    }

    public void setBuildingInfo(String buildingInfo) {
        this.buildingInfo = buildingInfo;
    }

    public Double getBuildingSize() {
        return buildingSize;
    }

    public void setBuildingSize(Double buildingSize) {
        this.buildingSize = buildingSize;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMerchantIdNo() {
        return merchantIdNo;
    }

    public void setMerchantIdNo(String merchantIdNo) {
        this.merchantIdNo = merchantIdNo;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getSignTotalPrice() {
        return signTotalPrice;
    }

    public void setSignTotalPrice(Integer signTotalPrice) {
        this.signTotalPrice = signTotalPrice;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getSigningMode() {
        return signingMode;
    }

    public void setSigningMode(String signingMode) {
        this.signingMode = signingMode;
    }

    public Date getPaybackDate() {
        return paybackDate;
    }

    public void setPaybackDate(Date paybackDate) {
        this.paybackDate = paybackDate;
    }

    public Date getPayStartDate() {
        return payStartDate;
    }

    public void setPayStartDate(Date payStartDate) {
        this.payStartDate = payStartDate;
    }

    public Date getContractTerDate() {
        return contractTerDate;
    }

    public void setContractTerDate(Date contractTerDate) {
        this.contractTerDate = contractTerDate;
    }

    public Integer getLeasebackPrice() {
        return leasebackPrice;
    }

    public void setLeasebackPrice(Integer leasebackPrice) {
        this.leasebackPrice = leasebackPrice;
    }

    public Integer getBackPremium() {
        return backPremium;
    }

    public void setBackPremium(Integer backPremium) {
        this.backPremium = backPremium;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getBeneficiaryIdNo() {
        return beneficiaryIdNo;
    }

    public void setBeneficiaryIdNo(String beneficiaryIdNo) {
        this.beneficiaryIdNo = beneficiaryIdNo;
    }

    public String getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(String bankInfo) {
        this.bankInfo = bankInfo;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBeneficiaryAddress() {
        return beneficiaryAddress;
    }

    public void setBeneficiaryAddress(String beneficiaryAddress) {
        this.beneficiaryAddress = beneficiaryAddress;
    }

    public Double getTariff() {
        return tariff;
    }

    public void setTariff(Double tariff) {
        this.tariff = tariff;
    }

    public Integer getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
    }

    @Override
    public String toString() {
        return "BasicRentInfo{" +
                "region='" + region + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", contractVersion='" + contractVersion + '\'' +
                ", subscriptionDate=" + subscriptionDate +
                ", signingDate=" + signingDate +
                ", buildingInfo='" + buildingInfo + '\'' +
                ", buildingSize=" + buildingSize +
                ", signer='" + signer + '\'' +
                ", phone='" + phone + '\'' +
                ", merchantIdNo='" + merchantIdNo + '\'' +
                ", originalPrice=" + originalPrice +
                ", totalPrice=" + totalPrice +
                ", signTotalPrice=" + signTotalPrice +
                ", contractStatus='" + contractStatus + '\'' +
                ", signingMode='" + signingMode + '\'' +
                ", paybackDate=" + paybackDate +
                ", payStartDate=" + payStartDate +
                ", contractTerDate=" + contractTerDate +
                ", leasebackPrice=" + leasebackPrice +
                ", backPremium=" + backPremium +
                ", beneficiary='" + beneficiary + '\'' +
                ", beneficiaryIdNo='" + beneficiaryIdNo + '\'' +
                ", bankInfo='" + bankInfo + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", beneficiaryAddress='" + beneficiaryAddress + '\'' +
                ", tariff=" + tariff +
                ", taxAmount=" + taxAmount +
                '}';
    }
}
