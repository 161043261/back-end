package com.bronya.mybatisdemo.mapper;

import com.bronya.mybatisdemo.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> getUserList();

    User getUserByUsername(String username);

    User getUserByUsernamePassword(String username, String password);

    User getUserByMap(Map<String, Object> map);

    int insertUser(User user);

    // @Param(keyName)
    User getUserByParam(@Param("p1") String username, @Param("p2") String password);
}
