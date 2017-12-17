package com.gilab.wjj.persistence.model;

import com.gilab.wjj.util.DateUtils;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class Contract implements Entity {
    private long id;
    private String contractNo;
    private SigningMode signingMode = SigningMode.DISPOSABLE;
    private long signingDate;
    private long buildingId;
    private long signerId;
    private int leasebackPrice;
    private long paybackDate;
    private List<Long> beneficiary;
    private long proposalId;
    private ContractStatus contractStatus = ContractStatus.UNSIGNED;
    private String logs;

    public Contract(){}

    public Contract(long id, String contractNo, SigningMode signingMode, long signingDate, long buildingId, long signerId,
                    int leasebackPrice, long paybackDate, List<Long> beneficiary, long proposalId,
                    ContractStatus contractStatus, String logs) {
        this.id = id;
        this.contractNo = contractNo;
        this.signingMode = signingMode;
        this.signingDate = signingDate;
        this.buildingId = buildingId;
        this.signerId = signerId;
        this.leasebackPrice = leasebackPrice;
        this.paybackDate = paybackDate;
        this.beneficiary = beneficiary;
        this.proposalId = proposalId;
        this.contractStatus = contractStatus;
        this.logs = logs;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
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

    public long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(long buildingId) {
        this.buildingId = buildingId;
    }

    public long getSignerId() {
        return signerId;
    }

    public void setSignerId(long signerId) {
        this.signerId = signerId;
    }

    public int getLeasebackPrice() {
        return leasebackPrice;
    }

    public void setLeasebackPrice(int leasebackPrice) {
        this.leasebackPrice = leasebackPrice;
    }

    public long getPaybackDate() {
        return paybackDate;
    }

    public void setPaybackDate(long paybackDate) {
        this.paybackDate = paybackDate;
    }

    public List<Long> getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(List<Long> beneficiary) {
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

    @Override
    public String toString(){
        return "Contract{" +
                "id=" + id +
                ", contractNo='" + contractNo + '\'' +
                ", signingMode='" + signingMode + '\'' +
                ", signingDate='" + DateUtils.datetimeString(signingDate) +'\'' +
                ", buildingId='" + buildingId + '\'' +
                ", signerId='" + signerId + '\'' +
                ", leasebackPrice='" + leasebackPrice + '\'' +
                ", paybackDate='" + DateUtils.datetimeString(paybackDate) + '\'' +
                ", beneficiary='" + beneficiary + '\'' +
                ", proposalId='" + proposalId + '\'' +
                ", contractStatus='" + contractStatus + '\'' +
                ", logs='" + logs + '\'' +
                '}';
    }

    public static class Builder{
        private long id;
        private String contractNo;
        private SigningMode signingMode = SigningMode.DISPOSABLE;
        private long signingDate;
        private long buildingId;
        private long signerId;
        private int leasebackPrice;
        private long paybackDate;
        private List<Long> beneficiary;
        private long proposalId;
        private ContractStatus contractStatus = ContractStatus.UNSIGNED;
        private String logs;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder contractNo(String contractNo){
            this.contractNo = contractNo;
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

        public Builder buildingId(long buildingId){
            this.buildingId = buildingId;
            return this;
        }

        public Builder signerId(long signerId){
            this.signerId = signerId;
            return this;
        }

        public Builder leasebackPrice(int leasebackPrice){
            this.leasebackPrice = leasebackPrice;
            return this;
        }

        public Builder paybackDate(long paybackDate){
            this.paybackDate = paybackDate;
            return this;
        }

        public Builder beneficiary(List<Long> beneficiary){
            this.beneficiary = beneficiary;
            return this;
        }

        public Builder proposalId(long proposalId){
            this.proposalId = proposalId;
            return this;
        }

        public Builder logs(String logs){
            this.logs = logs;
            return this;
        }

        public Contract build(){
            return new Contract(id, contractNo, signingMode, signingDate, buildingId, signerId, leasebackPrice, paybackDate,
                    beneficiary, proposalId, contractStatus, logs);
        }
    }
}
