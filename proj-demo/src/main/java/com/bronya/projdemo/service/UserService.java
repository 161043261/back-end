package com.bronya.projdemo.service;

import com.bronya.projdemo.dao.User;

public interface UserService {
    User selectUserById(Integer id);

    int insertUser(User user);

    int updateUser(User user);

    int updateAvatar(String avatar);

    int updatePwd(Integer id, String newPwd);

    User selectUserByUsername(String username);
}
