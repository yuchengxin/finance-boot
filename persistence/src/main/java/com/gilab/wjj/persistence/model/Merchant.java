package com.gilab.wjj.persistence.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class Merchant implements Entity, Cloneable{

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private long id;
    private String merchantName;
    private String merchantPhone;
    private String merchantIdNo;
    private String bankInfo;
    private String bankAccount;
    private String merchantAddress;

    public Merchant(){}

    public Merchant(long id, String merchantName, String merchantPhone, String merchantIdNo,
                    String bankInfo, String bankAccount, String merchantAddress) {
        this.id = id;
        this.merchantName = merchantName;
        this.merchantPhone = merchantPhone;
        this.merchantIdNo = merchantIdNo;
        this.bankInfo = bankInfo;
        this.bankAccount = bankAccount;
        this.merchantAddress = merchantAddress;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantPhone() {
        return merchantPhone;
    }

    public void setMerchantPhone(String merchantPhone) {
        this.merchantPhone = merchantPhone;
    }

    public String getMerchantIdNo() {
        return merchantIdNo;
    }

    public void setMerchantIdNo(String merchantIdNo) {
        this.merchantIdNo = merchantIdNo;
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

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", merchantName='" + merchantName + '\'' +
                ", merchantPhone='" + merchantPhone + '\'' +
                ", merchantIdNo='" + merchantIdNo + '\'' +
                ", bankInfo='" + bankInfo + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", merchantAddress='" + merchantAddress + '\'' +
                '}';
    }

    @Override
    public Merchant clone() {
        Merchant cloned = null;
        try {
            cloned = (Merchant) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("Failed to clone UserBean", e);
        }
        return cloned;
    }

    public static class Builder{
        private long id;
        private String merchantName;
        private String merchantPhone;
        private String merchantIdNo;
        private String bankInfo;
        private String bankAccount;
        private String merchantAddress;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder merchantName(String merchantName){
            this.merchantName = merchantName;
            return this;
        }

        public Builder merchantPhone(String merchantPhone){
            this.merchantPhone = merchantPhone;
            return this;
        }

        public Builder merchantIdNo(String merchantIdNo){
            this.merchantIdNo = merchantIdNo;
            return this;
        }

        public Builder bankInfo(String bankInfo){
            this.bankInfo = bankInfo;
            return this;
        }

        public Builder bankAccount(String bankAccount){
            this.bankAccount = bankAccount;
            return this;
        }

        public Builder merchantAddress(String merchantAddress){
            this.merchantAddress = merchantAddress;
            return this;
        }

        public Merchant build(){
            return new Merchant(id, merchantName, merchantPhone, merchantIdNo, bankInfo, bankAccount, merchantAddress);
        }
    }
}
