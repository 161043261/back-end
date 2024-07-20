package com.bronya.mybatisdemo.mapper;

import com.bronya.mybatisdemo.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> getUserList();

    User getUserByUsername(String username);

    User getUserByUsernamePassword(String username, String password);

    User getUserByMap(Map<String, Object> map);
}
