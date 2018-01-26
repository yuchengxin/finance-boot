package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yuankui on 12/18/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface ContractAgent extends Agent {

    ReqResult<Contract> getContract(long contractId);

    List<Contract> getContractWithFilter(Long filterStartTime, Long filterEndTime, String contractVersion, String buildingInfo,
                                         SigningMode signMode, ContractStatus contractStatus);

    List<AllBasicRentResult> getContractAndCalResultWithFilter(String contractNo, Long filterStartTime, Long filterEndTime, String contractVersion, String buildingInfo,
                                                    SigningMode signMode, ContractStatus contractStatus, String beneficiaryName, String beneficiaryPhone,
                                                    String beneficiaryIDNO, String beneficiaryAccount);

    ReqResult<Contract> createContract(Contract contract);

    SimpleReqResult updateContract(Contract contract);

    SimpleReqResult deleteContract(long contractId);

    ReqResultMap batchCreateContracts(List<BasicRentInfo> basicRentInfos);
}
