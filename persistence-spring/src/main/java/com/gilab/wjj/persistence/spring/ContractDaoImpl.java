package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.mapper.ContractMapper;
import com.gilab.wjj.persistence.model.Contract;
import com.gilab.wjj.persistence.model.ContractStatus;
import com.gilab.wjj.persistence.model.SigningMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Repository("ContractDao")
public class ContractDaoImpl implements ContractDao {
    @Autowired
    private ContractMapper mapper;

    @Override
    public Contract getContract(long contractId) {
        return mapper.selectContract(contractId);
    }

    @Override
    public List<Contract> getContractWithFilter(Long filterStartTime, Long filterEndTime, String contractVersion,
                                                Double buildingStartSize, Double buildingEndSize, SigningMode signingMode,
                                                ContractStatus status) {
        return mapper.selectContractWithFilter(filterStartTime, filterEndTime, contractVersion, buildingStartSize, buildingEndSize,
                signingMode, status);
    }

    @Override
    public long createContract(Contract contract) {
        mapper.insertContract(contract);
        return contract.getId();
    }

    @Override
    public void updateContract(Contract contract) {
        mapper.updateContract(contract);
    }

    @Override
    public void deleteContract(long contractId) {
        mapper.deleteContract(contractId);
    }

    @Override
    public void batchUpdateContractStatus(List<Contract> contracts) {
        mapper.batchUpdateContractStatus(contracts);
    }

    @Override
    public void batchCreateContracts(List<Contract> contracts) {
        mapper.batchInsertContracts(contracts);
    }

    @Override
    public Contract getContractWithNo(String contractNo) {
        return mapper.selectContractWithNo(contractNo);
    }
}
