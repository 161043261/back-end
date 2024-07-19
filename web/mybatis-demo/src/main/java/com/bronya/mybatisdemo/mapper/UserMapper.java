package com.bronya.mybatisdemo.mapper;

import com.bronya.mybatisdemo.pojo.User;

import java.util.List;

public interface UserMapper {
    int insertUser();

    int updateUser();

    int deleteUser();

    User getUserById();

    List<User> getUsers();
}
