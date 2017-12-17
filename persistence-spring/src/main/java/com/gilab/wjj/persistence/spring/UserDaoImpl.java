package com.gilab.wjj.persistence.spring;

import com.gilab.wjj.persistence.dao.UserDao;
import com.gilab.wjj.persistence.mapper.UserMapper;
import com.gilab.wjj.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Repository("UserDao")
public class UserDaoImpl implements UserDao{

    @Autowired
    private UserMapper mapper;

    @Override
    public User getUser(long userId) {
        return mapper.selectUser(userId);
    }

    @Override
    public User getUserWithName(String username) {
        return mapper.selectUserWithName(username);
    }

    @Override
    public long createUser(User user) {
        mapper.insertUser(user);
        return user.getId();
    }

    @Override
    public void updateUser(User user) {
        mapper.updateUser(user);
    }

    @Override
    public void deleteUser(long userId) {
        mapper.deleteUser(userId);
    }
}
