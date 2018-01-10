package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.Contract;
import com.gilab.wjj.persistence.model.ContractStatus;
import com.gilab.wjj.persistence.model.SigningMode;

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

    List<Contract> getContractWithFilter(Long filterStartTime, Long filterEndTime, String contractVersion,
                                         Double buildingStartSize, Double buildingEndSize, SigningMode signingMode,
                                         ContractStatus status);

    long createContract(Contract contract);

    void updateContract(Contract contract);

    void deleteContract(long contractId);

    void batchUpdateContractStatus(List<Contract> contracts);

    void batchCreateContracts(List<Contract> contracts);

    Contract getContractWithNo(String contractNo);
}
