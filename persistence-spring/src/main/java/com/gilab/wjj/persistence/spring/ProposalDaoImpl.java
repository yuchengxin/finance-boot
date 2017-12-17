package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.ProposalDao;
import com.gilab.wjj.persistence.mapper.ProposalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Repository("ProposalDao")
public class ProposalDaoImpl implements ProposalDao {
    @Autowired
    private ProposalMapper mapperr;
}
