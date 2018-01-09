package com.gilab.wjj.core;


import com.gilab.wjj.persistence.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuankui on 10/31/17.
 */
public interface LoginAgent extends Agent {

    List<MenuList> getMenuByParentId(Integer parentId, Integer permission);

    List<PermissionList> getPermissionsWithFilter(String permission);

    Integer deletePermitionListById(Integer id);

    List<Map> menuList(Integer id);

    void addPermissionList(Long menuid, String permission);
}
