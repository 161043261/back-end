package com.bronya.projdemo.service.impl;

import com.bronya.projdemo.mapper.UserMapper;
import com.bronya.projdemo.pojo.User;
import com.bronya.projdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public int insertUser(User user) {
        String encryption = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(encryption);
        user.setCreateTime(LocalDateTime.now()); // override by `now()`
        user.setUpdateTime(LocalDateTime.now()); // override by `now()`
        return userMapper.insertUser(user);
    }
}
