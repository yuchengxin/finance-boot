package com.gilab.wjj.persistence.mapper;

import com.gilab.wjj.persistence.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yuankui on 12/17/17.
 * <p>
 * Desc:
 * <p>
 * Change:
 */
@Mapper
public interface UserMapper {
    User selectUser(@Param("id") long userId);

    User selectUserWithName(@Param("username") String username);

    List<User> selectUserWithFilter(@Param("username") String username);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(@Param("id") long userId);
}
