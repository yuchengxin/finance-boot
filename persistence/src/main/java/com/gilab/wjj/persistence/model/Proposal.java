package com.gilab.wjj.persistence.model;

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
public class Proposal implements Entity, Cloneable {

    private static final Logger logger = LoggerFactory.getLogger(User.class);

    private long id;
    private String proposalName;
    private int leasebackLife;
    private int marketCulLife;
    private int leasebackStages;
    private List<PeriodCalStandard> conf;
    private String proposalDes;

    public Proposal() {
    }

    public Proposal(long id, String proposalName, int leasebackLife, int marketCulLife, int leasebackStages,
                    List<PeriodCalStandard> conf, String proposalDes) {
        this.id = id;
        this.proposalName = proposalName;
        this.leasebackLife = leasebackLife;
        this.marketCulLife = marketCulLife;
        this.leasebackStages = leasebackStages;
        this.conf = conf;
        this.proposalDes = proposalDes;
    }

    public static Logger getLogger() {
        return logger;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProposalName() {
        return proposalName;
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName;
    }

    public int getLeasebackLife() {
        return leasebackLife;
    }

    public void setLeasebackLife(int leasebackLife) {
        this.leasebackLife = leasebackLife;
    }

    public int getMarketCulLife() {
        return marketCulLife;
    }

    public void setMarketCulLife(int marketCulLife) {
        this.marketCulLife = marketCulLife;
    }

    public int getLeasebackStages() {
        return leasebackStages;
    }

    public void setLeasebackStages(int leasebackStages) {
        this.leasebackStages = leasebackStages;
    }

    public String getProposalDes() {
        return proposalDes;
    }

    public void setProposalDes(String proposalDes) {
        this.proposalDes = proposalDes;
    }

    public List<PeriodCalStandard> getConf() {
        return conf;
    }

    public void setConf(List<PeriodCalStandard> conf) {
        this.conf = conf;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", proposalName='" + proposalName + '\'' +
                ", leasebackLife=" + leasebackLife +
                ", marketCulLife=" + marketCulLife +
                ", leasebackStages=" + leasebackStages +
                ", conf='" + conf + '\'' +
                ", proposalDes='" + proposalDes + '\'' +
                '}';
    }

    @Override
    public Proposal clone() {
        Proposal cloned = null;
        try {
            cloned = (Proposal) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error("Failed to clone UserBean", e);
        }
        return cloned;
    }

    public PeriodCalStandard getPeriodInfo(int period){
        for(PeriodCalStandard p: conf){
            if(p.getPeriod() == period){
                return p;
            }
        }
        return null;
    }

    public static class Builder{
        private long id;
        private String proposalName;
        private int leasebackLife;
        private int marketCulLife;
        private int leasebackStages;
        private List<PeriodCalStandard> conf;
        private String proposalDes;

        public Builder id(long id){
            this.id = id;
            return this;
        }

        public Builder proposalName(String proposalName){
            this.proposalName = proposalName;
            return this;
        }

        public Builder leasebackLife(int leasebackLife){
            this.leasebackLife = leasebackLife;
            return this;
        }

        public Builder marketCulLife(int marketCulLife){
            this.marketCulLife = marketCulLife;
            return this;
        }

        public Builder leasebackStages(int leasebackStages){
            this.leasebackStages = leasebackStages;
            return this;
        }

        public Builder conf(List<PeriodCalStandard> conf){
            this.conf = conf;
            return this;
        }

        public Builder proposalDes(String proposalDes){
            this.proposalDes = proposalDes;
            return this;
        }

        public Proposal build(){
            return new Proposal(id, proposalName, leasebackLife, marketCulLife, leasebackStages, conf, proposalDes);
        }
    }
}
