package com.gilab.wjj.core.user;

import com.gilab.wjj.core.LoginAgent;
import com.gilab.wjj.core.UserAgent;
import com.gilab.wjj.persistence.dao.LoginDao;
import com.gilab.wjj.persistence.dao.UserDao;
import com.gilab.wjj.persistence.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("loginAgent")
public class LoginManager implements LoginAgent{

    @Autowired
    private LoginDao loginDao;

    @Override
    public List<MenuList> getMenuByParentId(Integer parentId) {
        return loginDao.getMenuByParentId(parentId);
    }

    @Override
    public List<PermissionList> getPermissionsWithFilter(String permission){return loginDao.getPermissionsWithFilter(permission);}

    @Override
    public Integer deletePermitionListById(Integer id) {
        return loginDao.deletePermitionListById(id);
    }

    @Override
    public  List<Map> menuList(Integer id){
        return loginDao.menuList(id);
    }

    @Override
    public void addPermissionList(Long menuid,String permission){
        loginDao.addPermissionList(menuid,permission);
    }
}
