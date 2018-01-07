package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.LoginDao;
import com.gilab.wjj.persistence.dao.UserDao;
import com.gilab.wjj.persistence.mapper.LoginMapper;
import com.gilab.wjj.persistence.mapper.UserMapper;
import com.gilab.wjj.persistence.model.MenuList;
import com.gilab.wjj.persistence.model.PermissionList;
import com.gilab.wjj.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
@Repository("LoginDao")
public class LoginDaoImpl implements LoginDao{

    @Autowired
    private LoginMapper mapper;

    @Override
    public List<MenuList> getMenuByParentId(Integer parentId) {
        return mapper.getMenuByParentId(parentId);
    }

    @Override
    public List<PermissionList> getPermissionsWithFilter(String permission) {
        return mapper.getPermissionsWithFilter(permission);
    }

    @Override
    public Integer deletePermitionListById(Integer id) {
        return mapper.deletePermitionListById(id);
    }

    @Override
    public  List<Map> menuList(Integer id){
        return mapper.menuList(id);
    }

    @Override
    public void addPermissionList(Long menuid, String permission){
        mapper.addPermissionList(menuid,permission);
    }
}
