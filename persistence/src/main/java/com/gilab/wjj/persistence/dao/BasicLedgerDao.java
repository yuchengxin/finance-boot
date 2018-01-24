package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.BasicLedger;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface BasicLedgerDao {

    BasicLedger getBasicLedger(long basicLedgerId);

    List<BasicLedger> getBasicLedgerWithContract(long contractId);

    List<BasicLedger> getBasicLedgerWithContractNo(String contractNo);

    long createBasicLedger(BasicLedger basicLedger);

    void batchCreateBasicLedgers(List<BasicLedger> basicLedgers);

    void updateBasicLedger(BasicLedger basicLedger);

    void deleteBasicLedger(long basicLedgerId);
}
