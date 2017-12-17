package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.BuildingDao;
import com.gilab.wjj.persistence.mapper.BuildingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Repository("BuildingDao")
public class BuildingDaoImpl implements BuildingDao {
    @Autowired
    private BuildingMapper mapper;
}
