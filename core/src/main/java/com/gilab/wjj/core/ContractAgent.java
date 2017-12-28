package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.*;

import java.util.List;

/**
 * Created by yuankui on 12/18/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface ContractAgent extends Agent {

    ReqResult<Contract> getContract(long contractId);

    List<Contract> getContractWithFilter(Long filterStartTime, Long filterEndTime, String contractVersion, Double buildingStartSize,
                                         Double buildingEndSize, SigningMode signMode, ContractStatus contractStatus);

    ReqResult<Contract> createContract(Contract contract);


    SimpleReqResult updateContract(Contract contract);

    SimpleReqResult deleteContract(long contractId);
}
