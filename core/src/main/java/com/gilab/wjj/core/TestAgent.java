package com.gilab.wjj.core;


import com.gilab.wjj.persistence.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuankui on 10/31/17.
 */
public interface TestAgent extends Agent {
    List<MenuList> getMenuByParentId(Integer parentId);
    List<HashMap> getMerchantByNameAndId(HashMap map);
    Integer addContract(HashMap map);
    ArrayList<Map<String,Object>> getContract(HashMap map);
    ArrayList<Map<String,Object>> contractSearch(HashMap map);
    ArrayList<Map<String,Object>> searchMerchant(HashMap map);
    Integer addMerchant(HashMap map);

}
