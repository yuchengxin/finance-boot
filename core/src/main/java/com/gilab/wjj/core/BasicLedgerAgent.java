package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.*;

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

}
