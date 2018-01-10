package com.gilab.wjj.persistence.model;


import com.gilab.wjj.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private Long subscriptionDate;
    private List<Merchant> signer;
    private SigningMode signingMode = SigningMode.DISPOSABLE;
    private SigningStatus signingStatus = SigningStatus.UNSIGNED;
    private Long signingDate;
    private String buildingInfo;
    private Double buildingSize;
    private Integer originalPrice;
    private Integer totalPrice;
    private Integer signTotalPrice;
    private Integer leasebackPrice;
    private Integer backPremium;
    private Long payStartDate;
    private Long contractTerDate;
    private Long paybackDate;
    private Merchant beneficiary;
    private Long proposalId;
    private ContractStatus contractStatus = ContractStatus.UNSTARTED;
//    private Double tariff;
//    private Integer taxAmount;
    private String logs;


    public Contract(){
    }

    public Contract(long id, String region, String contractNo, String contractVersion, Long subscriptionDate, List<Merchant> signer, SigningMode signingMode, SigningStatus signingStatus, Long signingDate, String buildingInfo, Double buildingSize, Integer originalPrice, Integer totalPrice, Integer signTotalPrice, Integer leasebackPrice, Integer backPremium, Long payStartDate, Long contractTerDate, Long paybackDate, Merchant beneficiary, Long proposalId, ContractStatus contractStatus, String logs) {
        this.id = id;
        this.region = region;
        this.contractNo = contractNo;
        this.contractVersion = contractVersion;
        this.subscriptionDate = subscriptionDate;
        this.signer = signer;
        this.signingMode = signingMode;
        this.signingStatus = signingStatus;
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
        this.logs = logs;
    }

    @Override
    public long getId() {
        return id;
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

    public Long getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Long subscriptionDate) {
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

    public SigningStatus getSigningStatus() {
        return signingStatus;
    }

    public void setSigningStatus(SigningStatus signingStatus) {
        this.signingStatus = signingStatus;
    }

    public Long getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(Long signingDate) {
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

    public Long getPayStartDate() {
        return payStartDate;
    }

    public void setPayStartDate(Long payStartDate) {
        this.payStartDate = payStartDate;
    }

    public Long getContractTerDate() {
        return contractTerDate;
    }

    public void setContractTerDate(Long contractTerDate) {
        this.contractTerDate = contractTerDate;
    }

    public Long getPaybackDate() {
        return paybackDate;
    }

    public void setPaybackDate(Long paybackDate) {
        this.paybackDate = paybackDate;
    }

    public Merchant getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(Merchant beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Long getProposalId() {
        return proposalId;
    }

    public void setProposalId(Long proposalId) {
        this.proposalId = proposalId;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getLogs() {
        return logs;
    }

    public void setLogs(String logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", contractNo='" + contractNo + '\'' +
                ", contractVersion='" + contractVersion + '\'' +
                ", subscriptionDate=" + subscriptionDate +
                ", signer=" + signer +
                ", signingMode=" + signingMode +
                ", signingStatus=" + signingStatus +
                ", signingDate=" + signingDate +
                ", buildingInfo='" + buildingInfo + '\'' +
                ", buildingSize=" + buildingSize +
                ", originalPrice=" + originalPrice +
                ", totalPrice=" + totalPrice +
                ", signTotalPrice=" + signTotalPrice +
                ", leasebackPrice=" + leasebackPrice +
                ", backPremium=" + backPremium +
                ", payStartDate=" + payStartDate +
                ", contractTerDate=" + contractTerDate +
                ", paybackDate=" + paybackDate +
                ", beneficiary=" + beneficiary +
                ", proposalId=" + proposalId +
                ", contractStatus=" + contractStatus +
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

    public BasicRentInfo contract2BasicRentInfo(){
        Map<String, String> signerInfo = splitMerchant(signer);
        return new BasicRentInfo.Builder()
                .region(region)
                .contractNo(contractNo)
                .contractVersion(contractVersion)
                .subscriptionDate(DateUtils.convertUtilDate(subscriptionDate))
                .signer(signerInfo.get("name"))
                .phone(signerInfo.get("phone"))
                .merchantIdNo(signerInfo.get("IdNo"))
                .signingDate(DateUtils.convertUtilDate(signingDate))
                .signingMode(signingMode.getDescription())
                .buildingInfo(buildingInfo)
                .buildingSize(buildingSize)
                .originalPrice(originalPrice)
                .totalPrice(totalPrice)
                .signTotalPrice(signTotalPrice)
                .leasebackPrice(leasebackPrice)
                .backPremium(backPremium)
                .payStartDate(DateUtils.convertUtilDate(paybackDate))
                .contractTerDate(DateUtils.convertUtilDate(contractTerDate))
                .paybackDate(DateUtils.convertUtilDate(paybackDate))
                .beneficiary(beneficiary.getMerchantName())
                .beneficiaryIdNo(beneficiary.getMerchantIdNo())
                .beneficiaryAddress(beneficiary.getMerchantAddress())
                .bankInfo(beneficiary.getBankInfo())
                .bankAccount(beneficiary.getBankAccount())
                .signingStatus(signingStatus.getDescription())
                .build();
    }

    private Map<String, String> splitMerchant(List<Merchant> merchants){
        StringBuilder merchantNames = new StringBuilder();
        StringBuilder merchantPhones = new StringBuilder();
        StringBuilder merchantIdNos = new StringBuilder();
        for (Merchant m : merchants){
            merchantNames.append(m.getMerchantName()).append(",");
            merchantPhones.append(m.getMerchantPhone()).append(",");
            merchantIdNos.append(m.getMerchantIdNo()).append(",");
        }
        Map<String, String> map = new HashMap<>();
        map.put("name", merchantNames.substring(0, merchantNames.length() - 1));
        map.put("phone", merchantPhones.substring(0, merchantPhones.length() - 1));
        map.put("IdNo", merchantIdNos.substring(0, merchantIdNos.length() - 1));
        return map;
    }

    public static class Builder{
        private long id;
        private String region;
        private String contractNo;
        private String contractVersion;
        private Long subscriptionDate;
        private List<Merchant> signer;
        private SigningMode signingMode = SigningMode.DISPOSABLE;
        private SigningStatus signingStatus = SigningStatus.UNSIGNED;
        private Long signingDate;
        private String buildingInfo;
        private Double buildingSize;
        private Integer originalPrice;
        private Integer totalPrice;
        private Integer signTotalPrice;
        private Integer leasebackPrice;
        private Integer backPremium;
        private Long payStartDate;
        private Long contractTerDate;
        private Long paybackDate;
        private Merchant beneficiary;
        private Long proposalId;
        private ContractStatus contractStatus = ContractStatus.UNSTARTED;
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

        public Builder subscriptionDate(Long subscriptionDate){
            this.subscriptionDate = subscriptionDate;
            return this;
        }

        public Builder signingMode(SigningMode signingMode){
            this.signingMode = signingMode;
            return this;
        }

        public Builder signingStatus(SigningStatus signingStatus){
            this.signingStatus = signingStatus;
            return this;
        }

        public Builder signingDate(Long signingDate){
            this.signingDate = signingDate;
            return this;
        }

        public Builder buildingInfo(String buildingInfo){
            this.buildingInfo = buildingInfo;
            return this;
        }

        public Builder buildingSize(Double buildingSize){
            this.buildingSize = buildingSize;
            return this;
        }

        public Builder signer(List<Merchant> signer){
            this.signer = signer;
            return this;
        }

        public Builder originalPrice(Integer originalPrice){
            this.originalPrice = originalPrice;
            return this;
        }

        public Builder totalPrice(Integer totalPrice){
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder signTotalPrice(Integer signTotalPrice){
            this.signTotalPrice = signTotalPrice;
            return this;
        }

        public Builder leasebackPrice(Integer leasebackPrice){
            this.leasebackPrice = leasebackPrice;
            return this;
        }

        public Builder backPremium(Integer backPremium){
            this.backPremium = backPremium;
            return this;
        }

        public Builder payStartDate(Long payStartDate){
            this.payStartDate = payStartDate;
            return this;
        }

        public Builder contractTerDate(Long contractTerDate){
            this.contractTerDate = contractTerDate;
            return this;
        }

        public Builder paybackDate(Long paybackDate){
            this.paybackDate = paybackDate;
            return this;
        }

        public Builder beneficiary(Merchant beneficiary){
            this.beneficiary = beneficiary;
            return this;
        }

        public Builder proposalId(Long proposalId){
            this.proposalId = proposalId;
            return this;
        }

        public Builder contractStatus(ContractStatus contractStatus){
            this.contractStatus = contractStatus;
            return this;
        }

        public Builder logs(String logs){
            this.logs = logs;
            return this;
        }

        public Contract build(){
            return new Contract(id, region, contractNo, contractVersion, subscriptionDate, signer, signingMode, signingStatus, signingDate,
                    buildingInfo, buildingSize, originalPrice, totalPrice, signTotalPrice, leasebackPrice, backPremium,
                    payStartDate, contractTerDate, paybackDate, beneficiary, proposalId, contractStatus, logs);
        }
    }
}
