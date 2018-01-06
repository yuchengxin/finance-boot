package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.User;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
public interface UserDao {

    User getUser(long userId);

    User getUserWithName(String username);

    List<User> getUserWithFilter(String username);

    long createUser(User user);

    void updateUser(User user);

    void deleteUser(long userId);
}
