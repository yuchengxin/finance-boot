package com.gilab.wjj.persistence.dao;

import com.gilab.wjj.persistence.model.User;

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

    long createUser(User user);

    void updateUser(User user);

    void deleteUser(long userId);
}
