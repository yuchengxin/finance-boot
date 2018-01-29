package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.BasicLedgerDao;
import com.gilab.wjj.persistence.mapper.BasicLedgerMapper;
import com.gilab.wjj.persistence.model.BasicLedger;
import com.gilab.wjj.persistence.model.PayStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Repository("BasicLedgerDao")
public class BasicLedgerDaoImpl implements BasicLedgerDao {
    @Autowired
    private BasicLedgerMapper mapper;

    @Override
    public List<BasicLedger> getLedger(long contractId) {
        return mapper.getLedger(contractId);
    }

    @Override
    public void batchUpdateLedgers(List<BasicLedger> ledgers) {
        mapper.batchUpdateLedgers(ledgers);
    }

    public BasicLedger getBasicLedger(long basicLedgerId) {
        return mapper.selectBasicLedger(basicLedgerId);
    }

    @Override
    public List<BasicLedger> getBasicLedgerWithContract(long contractId) {
        return mapper.selectBasicLedgerWithContract(contractId);
    }

    @Override
    public List<BasicLedger> getBasicLedgerWithContractNo(String contractNo) {
        return mapper.selectBasicLedgerWithContractNo(contractNo);
    }

    @Override
    public long createBasicLedger(BasicLedger basicLedger) {
        mapper.insertBasicLedger(basicLedger);
        return basicLedger.getId();
    }

    @Override
    public void batchCreateBasicLedgers(List<BasicLedger> basicLedgers) {
        mapper.batchInsertBasicLedgers(basicLedgers);
    }

    @Override
    public void updateBasicLedger(BasicLedger basicLedger) {
        mapper.updateBasicLedger(basicLedger);
    }

    @Override
    public void deleteBasicLedger(long basicLedgerId) {
        mapper.deleteBasicLedger(basicLedgerId);
    }

    @Override
    public List<HashMap> getLedgerWithFilter(PayStatus payStatus,String contractNo,String benefitName,String benefitPhone,String buildingInfo,String benefitBankAccount,
                                             Long planPayDateStart,Long planPayDateEnd,Long actualPayDateStart,Long actualPayDateEnd) {
        return mapper.getLedgerWithFilter(payStatus,contractNo,benefitName,benefitPhone,buildingInfo,benefitBankAccount,planPayDateStart,planPayDateEnd,actualPayDateStart,actualPayDateEnd);
    }

    @Override
    public void payLedger(List<Long> selectedidList) {
        mapper.payLedger(selectedidList);
    }
}
