package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.Contract;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface ContractDao {
    Contract getContract(long contractId);

    List<Contract> getContractWithFilter();

    long createContract(Contract contract);

    void updateContract(Contract contract);

    void deleteContract(long contractId);
}
