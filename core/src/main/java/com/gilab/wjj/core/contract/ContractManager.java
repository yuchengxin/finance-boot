package com.gilab.wjj.core.contract;

import com.gilab.wjj.core.ContractAgent;
import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.dao.MerchantDao;
import com.gilab.wjj.persistence.dao.ProposalDao;
import com.gilab.wjj.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuankui on 12/20/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Service("contractAgent")
public class ContractManager implements ContractAgent {

    @Autowired
    private ContractDao contractDao;

    @Autowired
    private MerchantDao merchantDao;

    @Autowired
    private ProposalDao proposalDao;

    @Override
    public ReqResult<Contract> getContract(long contractId) {
        Contract contract = contractDao.getContract(contractId);
        if (contract == null) {
            return ReqResult.fail("Cannot find contract[%d]", contractId);
        } else {
            return ReqResult.success(contract, "contract found.");
        }
    }

    @Override
    public List<Contract> getContractWithFilter(String contractNo, String phone, String signer, String merchantIdNo) {
        return null;
    }

    @Override
    public ReqResult<Contract> createContract(Contract contract) {
        long id = contractDao.createContract(contract);
        Contract newContract = contract.clone();
        newContract.setId(id);
        return ReqResult.success(newContract, "contract[%d] created.", id);
    }

    @Override
    public SimpleReqResult updateContract(Contract contract) {
        return null;
    }

    @Override
    public SimpleReqResult deleteContract(long contractId) {
        return null;
    }
}
