package com.gilab.wjj.core.user;

import com.gilab.wjj.core.TestAgent;
import com.gilab.wjj.core.UserAgent;
import com.gilab.wjj.persistence.dao.TestDao;
import com.gilab.wjj.persistence.dao.UserDao;
import com.gilab.wjj.persistence.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Service("testAgent")
public class TestManager implements TestAgent {

    @Autowired
    private TestDao testDao;

    @Override
    public List<MenuList> getMenuByParentId(Integer parentId) {
        return testDao.getMenuByParentId(parentId);
    }

    @Override
    public List<HashMap> getMerchantByNameAndId(HashMap map) {
        return testDao.getMerchantByNameAndId(map);
    }

    @Override
    public Integer addContract(HashMap map) {
        return testDao.addContract(map);
    }

    @Override
    public ArrayList<Map<String, Object>> getContract(HashMap map) {
        return testDao.getContract(map);
    }

    @Override
    public ArrayList<Map<String, Object>> contractSearch(HashMap map) {
        return testDao.contractSearch(map);
    }

    @Override
    public ArrayList<Map<String, Object>> searchMerchant(HashMap map) {
        return testDao.searchMerchant(map);
    }

    @Override
    public Integer addMerchant(HashMap map) {
        return testDao.addMerchant(map);
    }
}
