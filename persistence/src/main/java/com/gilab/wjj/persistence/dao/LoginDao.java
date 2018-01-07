package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.MenuList;
import com.gilab.wjj.persistence.model.PermissionList;
import com.gilab.wjj.persistence.model.User;

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
public interface LoginDao {

    List<MenuList> getMenuByParentId(Integer parentId);

    List<PermissionList> getPermissionsWithFilter(String permission);

    Integer deletePermitionListById(Integer id);

    List<Map> menuList(Integer id);

    void addPermissionList(Long menuid,String permission);
}
