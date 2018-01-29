package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by che on 2018/1/24.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface BasicLedgerAgent extends Agent {

    List<BasicLedger> getLedger(long contractId);

    ReqResultMap batchUpdateLedgers(List<BasicLedgerInfo> basicLedgerInfos);

    List<HashMap> getLedgerWithFilter(PayStatus payStatus,String contractNo,String benefitName,String benefitPhone,String buildingInfo,String benefitBankAccount,
                                      Long planPayDateStart,Long planPayDateEnd,Long actualPayDateStart,Long actualPayDateEnd);

    void payLedger(List<Long> selectedidList);

}
