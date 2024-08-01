package com.bronya.projdemo.service;

import com.bronya.projdemo.pojo.User;

public interface UserService {
    User selectUserById(int id);

    int insertUser(User user);

    int updateUser(User user);

    int updateAvatar(String avatarUrl);

    int updatePwd(int id, String newPwd);

    User selectUserByUsername(String username);
}
