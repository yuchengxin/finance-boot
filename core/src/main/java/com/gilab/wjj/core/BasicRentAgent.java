package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.*;

import java.util.List;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface BasicRentAgent extends Agent {

     List<BasicLedger> calBasicRentDetail(long contractId);

//     ReqResult<BasicRentMonthResult> calBasicRentMonth(long contractId, long date);
//
//     ReqResult<BasicRentYearResult> calBasicRentYear(long contractId, int year);
//
//     ReqResult<BasicRentPeriodResult> calBasicRentPeriod(long contractId, int period);

     List<BasicLedger> preCalBasicRentDetail(long paybackDate, int leasebackPrice, long proposalId);

     List<BasicRentMonthResult> monthlyRentReport(long date);
}
