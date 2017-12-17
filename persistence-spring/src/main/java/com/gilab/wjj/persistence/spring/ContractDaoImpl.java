package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.ContractDao;
import com.gilab.wjj.persistence.mapper.ContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
