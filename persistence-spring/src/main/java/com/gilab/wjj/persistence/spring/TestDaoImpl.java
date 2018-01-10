package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.TestDao;
import com.gilab.wjj.persistence.dao.UserDao;
import com.gilab.wjj.persistence.mapper.TestMapper;
import com.gilab.wjj.persistence.mapper.UserMapper;
import com.gilab.wjj.persistence.model.MenuList;
import com.gilab.wjj.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
@Repository("TestDao")
public class TestDaoImpl implements TestDao{

    @Autowired
    private TestMapper mapper;

    @Override
    public List<MenuList> getMenuByParentId(Integer parentId) {
        return mapper.getMenuByParentId(parentId);
    }

    @Override
    public List<HashMap> getMerchantByNameAndId(HashMap map) {
        return mapper.getMerchantByNameAndId(map);
    }

    @Override
    public Integer addContract(HashMap map) {
        return mapper.addContract(map);
    }

    @Override
    public ArrayList<Map<String, Object>> getContract(HashMap map) {
        return mapper.getContract(map);
    }

    @Override
    public ArrayList<Map<String, Object>> contractSearch(HashMap map) {
        return mapper.contractSearch(map);
    }

    @Override
    public ArrayList<Map<String, Object>> searchMerchant(HashMap map) {
        return mapper.searchMerchant(map);
    }

    @Override
    public Integer addMerchant(HashMap map) {
        return mapper.addMerchant(map);
    }
}
