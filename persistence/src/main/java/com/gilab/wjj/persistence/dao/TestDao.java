package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.MenuList;
import com.gilab.wjj.persistence.model.User;

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
public interface TestDao {
    List<MenuList> getMenuByParentId(Integer parentId);

    List<HashMap> getMerchantByNameAndId(HashMap map);

    Integer addContract(HashMap map);

    ArrayList<Map<String,Object>> getContract(HashMap map);

    ArrayList<Map<String,Object>> contractSearch(HashMap map);

    ArrayList<Map<String,Object>> searchMerchant(HashMap map);

    Integer addMerchant(HashMap map);
}
