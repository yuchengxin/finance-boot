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

    List<BasicLedger> getLedger(long contractId);

    void batchUpdateLedgers(List<BasicLedger> ledgers);
}
