package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.*;

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

     ReqResult<BasicRentYearResult> calBasicRentYear(long contractId, int year);

     ReqResult<BasicRentPeriodResult> calBasicRentPeriod(long contractId, int period);
}
