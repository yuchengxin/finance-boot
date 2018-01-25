package com.gilab.wjj.core.ledger;

import com.gilab.wjj.core.BasicLedgerAgent;
import com.gilab.wjj.persistence.dao.BasicLedgerDao;
import com.gilab.wjj.persistence.model.*;
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
        System.out.println(basicLedgerInfos);
        for(int i = 0 ; i < basicLedgerInfos.size(); i++){
            BasicLedger basicLedger = new BasicLedger();
//            basicLedger.setId(basicLedgerInfos.get(i).getId());
//            basicLedger.setContractId(basicLedgerInfos.get(i).getContractId());
//            basicLedger.setBeneficiaryId(basicLedgerInfos.get(i).getBeneficiaryId());
            basicLedger.setContractNo(basicLedgerInfos.get(i).getContractNo());
//            basicLedger.setCalFormula(basicLedgerInfos.get(i).getCalFormula());
            System.out.println("-chejian----"+i+"--:"+basicLedgerInfos.get(i)+"-----");
            System.out.println("-chejian----"+i+"--:"+basicLedgerInfos.get(i).getPlanPayDate()+"-----");
            if(basicLedgerInfos.get(i).getPlanPayDate()==null)continue;
            System.out.println("-chejian----"+i+"--:"+basicLedgerInfos.get(i).getPlanPayDate().getTime()+"-----");
            basicLedger.setPlanPayDate(basicLedgerInfos.get(i).getPlanPayDate().getTime());
            basicLedger.setPlanPayCountPre(basicLedgerInfos.get(i).getPlanPayCountPre());
            basicLedger.setPlanPayCountPost(basicLedgerInfos.get(i).getPlanPayCountPost());
            basicLedger.setActualPayDate(basicLedgerInfos.get(i).getActualPayDate().getTime());
            basicLedger.setActualPayCount(basicLedgerInfos.get(i).getActualPayCount());
//            basicLedger.setPayStatus(PayStatus.strLookup(basicLedgerInfos.get(i).getPayStatus()));
            if(basicLedgerInfos.get(i).getActualPayCount()>0){
                basicLedger.setPayStatus(PayStatus.SUCCESSFULPAID);
            }else{
                basicLedger.setPayStatus(PayStatus.FAILEDPAID);
            }
            ledgers.add(basicLedger);
        }

        Map<BasicRentInfo, String> resultFailed = new HashMap<>();
        Map<BasicRentInfo, String> resultSucceed = new HashMap<>();
        basicLedgerDao.batchUpdateLedgers(ledgers);
        return ReqResultMap.success(resultSucceed,"导入成功");
    }
}
