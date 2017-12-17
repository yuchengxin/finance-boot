package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.MerchantDao;
import com.gilab.wjj.persistence.mapper.MerchantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Repository("MerchantDao")
public class MerchantDaoImpl implements MerchantDao {
    @Autowired
    private MerchantMapper mapper;
}
