package com.gilab.wjj.persistence.mapper;

import com.gilab.wjj.persistence.model.BasicLedger;
import org.apache.ibatis.annotations.Mapper;

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
}
