package com.gilab.wjj.persistence.mapper;

import com.gilab.wjj.persistence.model.BasicLedger;
import com.gilab.wjj.persistence.model.PayStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Mapper
public interface BasicLedgerMapper {
    List<BasicLedger> getLedger(long contractId);

    void batchUpdateLedgers(List<BasicLedger> ledgers);

    BasicLedger selectBasicLedger(@Param("id") long basicLedgerId);

    List<BasicLedger> selectBasicLedgerWithContract(@Param("contractId") long contractId);

    List<BasicLedger> selectBasicLedgerWithContractNo(@Param("contractNo") String contractNo);

    void insertBasicLedger(BasicLedger basicLedger);

    void batchInsertBasicLedgers(List<BasicLedger> basicLedgers);

    void updateBasicLedger(BasicLedger basicLedger);

    void deleteBasicLedger(@Param("id") long basicLedgerId);

    List<HashMap> getLedgerWithFilter(@Param("payStatus") PayStatus payStatus,
                                      @Param("contractNo") String contractNo,
                                      @Param("benefitName") String benefitName,
                                      @Param("benefitPhone") String benefitPhone,
                                      @Param("buildingInfo") String buildingInfo,
                                      @Param("benefitBankAccount") String benefitBankAccount,
                                      @Param("planPayDateStart") Long planPayDateStart,
                                      @Param("planPayDateEnd") Long planPayDateEnd,
                                      @Param("actualPayDateStart") Long actualPayDateStart,
                                      @Param("actualPayDateEnd") Long actualPayDateEnd);

    void payLedger(List<Long> selectedidList);
}
