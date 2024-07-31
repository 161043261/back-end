package com.bronya.projdemo.service;

import com.bronya.projdemo.pojo.User;

public interface UserService {
    User selectUserByUsername(String username);

    int insertUser(User user);
}
