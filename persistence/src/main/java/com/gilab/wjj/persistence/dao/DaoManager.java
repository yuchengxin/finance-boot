package com.gilab.wjj.persistence.dao;

/**
 * Created by yuankui on 10/31/17.
 */
public interface DaoManager {
    UserDao user();

    ContractDao contract();

    MerchantDao merchant();

    BuildingDao building();

    ProposalDao proposal();

    BasicLedgerDao basicLedger();

    AddedLedgerDao addedLedger();
}
