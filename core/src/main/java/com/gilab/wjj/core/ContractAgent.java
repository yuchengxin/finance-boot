package com.gilab.wjj.core;

import com.gilab.wjj.persistence.model.Contract;
import com.gilab.wjj.persistence.model.ReqResult;
import com.gilab.wjj.persistence.model.SimpleReqResult;
import com.gilab.wjj.persistence.model.User;

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

    List<Contract> getContractWithFilter(String contractNo, String phone, String signer, String merchantIdNo);

    ReqResult<Contract> createContract(Contract contract);



    SimpleReqResult updateContract(Contract contract);

    SimpleReqResult deleteContract(long contractId);
}
