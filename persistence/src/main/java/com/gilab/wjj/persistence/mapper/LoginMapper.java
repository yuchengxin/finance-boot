package com.gilab.wjj.persistence.mapper;

import com.gilab.wjj.persistence.model.MenuList;
import com.gilab.wjj.persistence.model.PermissionList;
import com.gilab.wjj.persistence.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
@Mapper
public interface LoginMapper {
    List<MenuList> getMenuByParentId(@Param("parentId") Integer parentId);

    List<PermissionList> getPermissionsWithFilter(@Param("permission") String permission);

    Integer deletePermitionListById(@Param("id") Integer id);

    List<Map> menuList(@Param("id") Integer id);

    void addPermissionList(@Param("menuid") Long menuid,@Param("permission") String permission);
}
