package com.gilab.wjj.persistence.mapper;

import com.gilab.wjj.persistence.model.Contract;
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
public interface ContractMapper {
    Contract selectContract(@Param("id") long contractId);

    List<Contract> selectContractWithFilter();

    void insertContract(Contract contract);

    void updateContract(Contract contract);

    void deleteContract(@Param("id") long contractId);
}
