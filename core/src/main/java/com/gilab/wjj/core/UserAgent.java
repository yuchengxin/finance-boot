package com.gilab.wjj.core;


import com.gilab.wjj.persistence.model.Permission;
import com.gilab.wjj.persistence.model.ReqResult;
import com.gilab.wjj.persistence.model.SimpleReqResult;
import com.gilab.wjj.persistence.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yuankui on 10/31/17.
 */
public interface UserAgent extends Agent {

    ReqResult<User> getUser(long userId);

    ReqResult<User> getUserWithName(String username);

    List<User> getUserWithFilter(@Param("username") String username);

    ReqResult<User> createUser(User user);

    SimpleReqResult updateUser(User user);

    SimpleReqResult deleteUser(long userId);

    SimpleReqResult grant(long userId, List<Permission> permissions);

    SimpleReqResult revoke(long userId, List<Permission> permissions);

    List<Permission> getPermissions(long userId);
}
