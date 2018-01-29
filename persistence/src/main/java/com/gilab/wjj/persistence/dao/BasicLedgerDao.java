package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.BasicLedger;
import com.gilab.wjj.persistence.model.PayStatus;

import java.util.HashMap;
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

    BasicLedger getBasicLedger(long basicLedgerId);

    List<BasicLedger> getBasicLedgerWithContract(long contractId);

    List<BasicLedger> getBasicLedgerWithContractNo(String contractNo);

    long createBasicLedger(BasicLedger basicLedger);

    void batchCreateBasicLedgers(List<BasicLedger> basicLedgers);

    void updateBasicLedger(BasicLedger basicLedger);

    void deleteBasicLedger(long basicLedgerId);

    List<HashMap> getLedgerWithFilter(PayStatus payStatus,String contractNo,String benefitName,String benefitPhone,String buildingInfo,String benefitBankAccount,
                                      Long planPayDateStart,Long planPayDateEnd,Long actualPayDateStart,Long actualPayDateEnd);

    void payLedger(List<Long> selectedidList);
}
