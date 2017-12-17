package com.gilab.wjj.persistence.model;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public class Proposal implements Entity {
    private int id;
    private int leasebackLife;
    private int marketCulLife;
    private int leasebackStages;
    private String conf;
    private String proposalDes;

    public Proposal() {
    }

    public Proposal(int id, int leasebackLife, int marketCulLife, int leasebackStages, String conf, String proposalDes) {
        this.id = id;
        this.leasebackLife = leasebackLife;
        this.marketCulLife = marketCulLife;
        this.leasebackStages = leasebackStages;
        this.conf = conf;
        this.proposalDes = proposalDes;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getleasebackStages() {
        return leasebackStages;
    }

    public void setleasebackStages(int leasebackStages) {
        this.leasebackStages = leasebackStages;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getProposalDes() {
        return proposalDes;
    }

    public void setProposalDes(String proposalDes) {
        this.proposalDes = proposalDes;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", leasebackLife=" + leasebackLife +
                ", marketCulLife=" + marketCulLife +
                ", leasebackStages=" + leasebackStages +
                ", conf='" + conf + '\'' +
                ", proposalDes='" + proposalDes + '\'' +
                '}';
    }

    public static class Builder{
        private int id;
        private int leasebackLife;
        private int marketCulLife;
        private int leasebackStages;
        private String conf;
        private String proposalDes;

        public Builder id(int id){
            this.id = id;
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

        public Builder conf(String conf){
            this.conf = conf;
            return this;
        }

        public Builder proposalDes(String proposalDes){
            this.proposalDes = proposalDes;
            return this;
        }

        public Proposal build(){
            return new Proposal(id, leasebackLife, marketCulLife, leasebackStages, conf, proposalDes);
        }
    }
}
