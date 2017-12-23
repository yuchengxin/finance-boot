package com.gilab.wjj.persistence.model;


import com.gilab.wjj.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class Contract implements Entity, Cloneable {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private long id;
    private String region;
    private String contractNo;
    private String contractVersion;
    private long subscriptionDate;
    private List<Merchant> signer;
    private SigningMode signingMode = SigningMode.DISPOSABLE;
    private long signingDate;
    private String buildingInfo;
    private double buildingSize;
    private int originalPrice;
    private int totalPrice;
    private int signTotalPrice;
    private int leasebackPrice;
    private int backPremium;
    private long payStartDate;
    private long contractTerDate;
    private long paybackDate;
    private Merchant beneficiary;
    private long proposalId;
    private ContractStatus contractStatus = ContractStatus.UNSIGNED;
    private Double tariff;
    private Integer taxAmount;
    private String logs;


    public Contract(){
    }

    public Contract(long id, String region, String contractNo, String contractVersion, long subscriptionDate,
                    List<Merchant> signer, SigningMode signingMode, long signingDate, String buildingInfo,
                    double buildingSize, int originalPrice, int totalPrice, int signTotalPrice, int leasebackPrice,
                    int backPremium, long payStartDate, long contractTerDate, long paybackDate, Merchant beneficiary, long proposalId,
                    ContractStatus contractStatus, Double tariff, Integer taxAmount, String logs) {
        this.id = id;
        this.region = region;
        this.contractNo = contractNo;
        this.contractVersion = contractVersion;
        this.subscriptionDate = subscriptionDate;
        this.signer = signer;
        this.signingMode = signingMode;
        this.signingDate = signingDate;
        this.buildingInfo = buildingInfo;
        this.buildingSize = buildingSize;
        this.originalPrice = originalPrice;
        this.totalPrice = totalPrice;
        this.signTotalPrice = signTotalPrice;
        this.leasebackPrice = leasebackPrice;
        this.backPremium = backPremium;
        this.payStartDate = payStartDate;
        this.contractTerDate = contractTerDate;
        this.paybackDate = paybackDate;
        this.beneficiary = beneficiary;
        this.proposalId = proposalId;
        this.contractStatus = contractStatus;
        this.tariff = tariff;
        this.taxAmount = taxAmount;
        this.logs = logs;
    }

    public void setId(long id) {
        this.id = id;
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

    public long getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(long subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public List<Merchant> getSigner() {
        return signer;
    }

    public void setSigner(List<Merchant> signer) {
        this.signer = signer;
    }

    public SigningMode getSigningMode() {
        return signingMode;
    }

    public void setSigningMode(SigningMode signingMode) {
        this.signingMode = signingMode;
    }

    public long getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(long signingDate) {
        this.signingDate = signingDate;
    }

    public String getBuildingInfo() {
        return buildingInfo;
    }

    public void setBuildingInfo(String buildingInfo) {
        this.buildingInfo = buildingInfo;
    }

    public double getBuildingSize() {
        return buildingSize;
    }

    public void setBuildingSize(double buildingSize) {
        this.buildingSize = buildingSize;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getSignTotalPrice() {
        return signTotalPrice;
    }

    public void setSignTotalPrice(int signTotalPrice) {
        this.signTotalPrice = signTotalPrice;
    }

    public int getLeasebackPrice() {
        return leasebackPrice;
    }

    public void setLeasebackPrice(int leasebackPrice) {
        this.leasebackPrice = leasebackPrice;
    }

    public int getBackPremium() {
        return backPremium;
    }

    public void setBackPremium(int backPremium) {
        this.backPremium = backPremium;
    }

    public long getPaybackDate() {
        return paybackDate;
    }

    public void setPaybackDate(long paybackDate) {
        this.paybackDate = paybackDate;
    }

    public Merchant getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Merchant beneficiary) {
        this.beneficiary = beneficiary;
    }

    public long getProposalId() {
        return proposalId;
    }

    public void setProposalId(long proposalId) {
        this.proposalId = proposalId;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
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

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    @Override
    public long getId() {
        return id;
    }

    public long getPayStartDate() {
        return payStartDate;
    }

    public void setPayStartDate(long payStartDate) {
        this.payStartDate = payStartDate;
    }

    public long getContractTerDate() {
        return contractTerDate;
    }

    public void setContractTerDate(long contractTerDate) {
        this.contractTerDate = contractTerDate;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", contractVersion='" + contractVersion + '\'' +
                ", subscriptionDate=" + DateUtils.datetimeString(subscriptionDate, "yyyy-MM-dd") +
                ", signer=" + signer +
                ", signingMode=" + signingMode +
                ", signingDate=" + DateUtils.datetimeString(signingDate, "yyyy-MM-dd") +
                ", buildingInfo='" + buildingInfo + '\'' +
                ", buildingSize=" + buildingSize +
                ", originalPrice=" + originalPrice +
                ", totalPrice=" + totalPrice +
                ", signTotalPrice=" + signTotalPrice +
                ", leasebackPrice=" + leasebackPrice +
                ", backPremium=" + backPremium +
                ", payStartDate=" + DateUtils.datetimeString(payStartDate, "yyyy-MM-dd") +
                ", contractTerDate=" + DateUtils.datetimeString(contractTerDate, "yyyy-MM-dd") +
                ", paybackDate=" + DateUtils.datetimeString(paybackDate, "yyyy-MM-dd") +
                ", beneficiary=" + beneficiary +
                ", proposalId=" + proposalId +
                ", contractStatus=" + contractStatus +
                ", tariff=" + tariff +
                ", taxAmount=" + taxAmount +
                ", logs='" + logs + '\'' +
                '}';
    }

    @Override
    public Contract clone() {
        Contract cloned = null;
        try {
            cloned = (Contract) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("Failed to clone UserBean", e);
        }
        return cloned;
    }

    public static class Builder{
        private long id;
        private String region;
        private String contractNo;
        private String contractVersion;
        private long subscriptionDate;
        private List<Merchant> signer;
        private SigningMode signingMode = SigningMode.DISPOSABLE;
        private long signingDate;
        private String buildingInfo;
        private double buildingSize;
        private int originalPrice;
        private int totalPrice;
        private int signTotalPrice;
        private int leasebackPrice;
        private int backPremium;
        private long payStartDate;
        private long contractTerDate;
        private long paybackDate;
        private Merchant beneficiary;
        private long proposalId;
        private ContractStatus contractStatus = ContractStatus.UNSIGNED;
        private Double tariff;
        private Integer taxAmount;
        private String logs;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder region(String region){
            this.region = region;
            return this;
        }

        public Builder contractNo(String contractNo){
            this.contractNo = contractNo;
            return this;
        }

        public Builder contractVersion(String contractVersion){
            this.contractVersion = contractVersion;
            return this;
        }

        public Builder subscriptionDate(long subscriptionDate){
            this.subscriptionDate = subscriptionDate;
            return this;
        }

        public Builder signingMode(SigningMode signingMode){
            this.signingMode = signingMode;
            return this;
        }

        public Builder signingDate(long signingDate){
            this.signingDate = signingDate;
            return this;
        }

        public Builder buildingInfo(String buildingInfo){
            this.buildingInfo = buildingInfo;
            return this;
        }

        public Builder buildingSize(double buildingSize){
            this.buildingSize = buildingSize;
            return this;
        }

        public Builder signer(List<Merchant> signer){
            this.signer = signer;
            return this;
        }

        public Builder originalPrice(int originalPrice){
            this.originalPrice = originalPrice;
            return this;
        }

        public Builder totalPrice(int totalPrice){
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder signTotalPrice(int signTotalPrice){
            this.signTotalPrice = signTotalPrice;
            return this;
        }

        public Builder leasebackPrice(int leasebackPrice){
            this.leasebackPrice = leasebackPrice;
            return this;
        }

        public Builder backPremium(int backPremium){
            this.backPremium = backPremium;
            return this;
        }

        public Builder payStartDate(long payStartDate){
            this.payStartDate = payStartDate;
            return this;
        }

        public Builder contractTerDate(long contractTerDate){
            this.contractTerDate = contractTerDate;
            return this;
        }

        public Builder paybackDate(long paybackDate){
            this.paybackDate = paybackDate;
            return this;
        }

        public Builder beneficiary(Merchant beneficiary){
            this.beneficiary = beneficiary;
            return this;
        }

        public Builder proposalId(long proposalId){
            this.proposalId = proposalId;
            return this;
        }

        public Builder contractStatus(ContractStatus contractStatus){
            this.contractStatus = contractStatus;
            return this;
        }

        public Builder tariff(Double tariff){
            this.tariff = tariff;
            return this;
        }

        public Builder taxAmount(Integer taxAmount){
            this.taxAmount = taxAmount;
            return this;
        }

        public Builder logs(String logs){
            this.logs = logs;
            return this;
        }

        public Contract build(){
            return new Contract(id, region, contractNo, contractVersion, subscriptionDate, signer, signingMode, signingDate,
                    buildingInfo, buildingSize, originalPrice, totalPrice, signTotalPrice, leasebackPrice, backPremium,
                    payStartDate, contractTerDate, paybackDate, beneficiary, proposalId, contractStatus, tariff, taxAmount, logs);
        }
    }
}
