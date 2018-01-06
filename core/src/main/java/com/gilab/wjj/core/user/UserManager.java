package com.gilab.wjj.core.user;

import com.gilab.wjj.core.UserAgent;
import com.gilab.wjj.persistence.dao.UserDao;
import com.gilab.wjj.persistence.model.Permission;
import com.gilab.wjj.persistence.model.ReqResult;
import com.gilab.wjj.persistence.model.SimpleReqResult;
import com.gilab.wjj.persistence.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Service("userAgent")
public class UserManager implements UserAgent {

    @Autowired
    private UserDao userDao;

    @Override
    public ReqResult<User> getUser(long userId) {
        User user = userDao.getUser(userId);
        if (user == null) {
            return ReqResult.fail("Cannot find user[%d]", userId);
        } else {
            return ReqResult.success(user, "User found.");
        }
    }

    @Override
    public ReqResult<User> getUserWithName(String username) {
        User user = userDao.getUserWithName(username);
        if (user == null) {
            return ReqResult.fail("Cannot find user with " + username);
        } else {
            return ReqResult.success(user, "User found.");
        }
    }

    @Override
    public List<User> getUserWithFilter(@Param("username") String username) {
        return userDao.getUserWithFilter(username);
    }

    @Override
    public ReqResult<User> createUser(User user) {
        long id = userDao.createUser(user);
        User newUser = user.clone();
        newUser.setId(id);
        return ReqResult.success(newUser, "User[%d] created.", id);
    }

    @Override
    public SimpleReqResult updateUser(User user) {
        User origUser = userDao.getUser(user.getId());
        if (origUser == null) return SimpleReqResult.fail("Cannot find User[%d]", user.getId());
        userDao.updateUser(user);
        return SimpleReqResult.success("User[%d] updated.", user.getId());
    }

    @Override
    public SimpleReqResult deleteUser(long userId) {
        User user = userDao.getUser(userId);
        if (user == null) return SimpleReqResult.fail("Cannot find user[%d]", userId);
        userDao.deleteUser(userId);
        return SimpleReqResult.success("User[%d] removed.", userId);
    }

    @Override
    public SimpleReqResult grant(long userId, List<Permission> permissions) {
        return null;
    }

    @Override
    public SimpleReqResult revoke(long userId, List<Permission> permissions) {
        return null;
    }

    @Override
    public List<Permission> getPermissions(long userId) {
        return null;
    }
}
