package com.gilab.wjj.persistence.mapper;

import com.gilab.wjj.persistence.model.BasicLedger;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    BasicLedger selectBasicLedger(@Param("id") long basicLedgerId);

    List<BasicLedger> selectBasicLedgerWithContract(@Param("contractId") long contractId);

    List<BasicLedger> selectBasicLedgerWithContractNo(@Param("contractNo") String contractNo);

    void insertBasicLedger(BasicLedger basicLedger);

    void batchInsertBasicLedgers(List<BasicLedger> basicLedgers);

    void updateBasicLedger(BasicLedger basicLedger);

    void deleteBasicLedger(@Param("id") long basicLedgerId);
}
