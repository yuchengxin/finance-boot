package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.BasicRentMonthResult;
import com.gilab.wjj.persistence.model.BasicRentResult;
import com.gilab.wjj.persistence.model.Contract;
import com.gilab.wjj.persistence.model.ReqResult;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface BasicRentAgent extends Agent {

     ReqResult<BasicRentResult>  calBasicRentDetail(long contractId);

     ReqResult<BasicRentMonthResult> calBasicRentMonth(long contractId, long date);

     ReqResult<BasicRentResult> calBasicRentPeriod(long contractId, int period);
}
