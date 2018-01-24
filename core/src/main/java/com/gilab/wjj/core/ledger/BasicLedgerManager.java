package com.gilab.wjj.core.ledger;

import com.gilab.wjj.core.BasicLedgerAgent;
import com.gilab.wjj.persistence.dao.BasicLedgerDao;
import com.gilab.wjj.persistence.model.BasicLedger;
import com.gilab.wjj.persistence.model.BasicLedgerInfo;
import com.gilab.wjj.persistence.model.BasicRentInfo;
import com.gilab.wjj.persistence.model.ReqResultMap;
import com.gilab.wjj.util.logback.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by che on 2018/1/24.
 */
@Service("basicLedgerAgent")
public class BasicLedgerManager implements BasicLedgerAgent{
   private static final Logger logger = LoggerFactory.getLogger(BasicLedgerManager.class);

    @Autowired
    BasicLedgerDao basicLedgerDao;

    @Override
    public List<BasicLedger> getLedger(long contractId) {
        return basicLedgerDao.getLedger(contractId);
    }

    @Override
    public ReqResultMap batchUpdateLedgers(List<BasicLedgerInfo> basicLedgerInfos) {
        List<BasicLedger> ledgers = new ArrayList<>();
        for(int i = 0 ; i < basicLedgerInfos.size(); i++){
            BasicLedger basicLedger = new BasicLedger();
            basicLedger.setContractId(basicLedgerInfos.get(i).getContractId());
            basicLedger.setPlanPayDate(basicLedgerInfos.get(i).getPlanPayDate().getTime());
            basicLedger.setPlanPayCount(basicLedgerInfos.get(i).getPlanPayCount());
            basicLedger.setActualPayDate(basicLedgerInfos.get(i).getActualPayDate().getTime());
            basicLedger.setActualPayCount(basicLedgerInfos.get(i).getActualPayCount());
            ledgers.add(basicLedger);
        }

        Map<BasicRentInfo, String> resultFailed = new HashMap<>();
        Map<BasicRentInfo, String> resultSucceed = new HashMap<>();
        System.out.println(ledgers);
        System.out.println(ledgers.get(0).getPlanPayDate());
        basicLedgerDao.batchUpdateLedgers(ledgers);
        return ReqResultMap.success(resultSucceed,"导入成功");
    }
}
