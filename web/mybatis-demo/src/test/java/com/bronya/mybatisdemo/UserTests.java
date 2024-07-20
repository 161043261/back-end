package com.bronya.mybatisdemo;

import com.bronya.mybatisdemo.mapper.UserMapper;
import com.bronya.mybatisdemo.pojo.User;
import com.bronya.mybatisdemo.util.MapperUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// pojo/* (entity classes)
// -> mapper/* (mapping interfaces)
// -> MyBatis proxy
// -> mapping classes
// -> tables
class UserTests {

    @Test
    public void testGetUserList() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        List<User> users = mapper.getUserList();
        users.forEach(System.out::println);
        System.out.println();
    }

    @Test
    public void testGetUserByName() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        User admin = mapper.getUserByUsername("admin");
        System.out.println(admin + "\n");
    }

    @Test
    public void testGetUserByUsernamePassword() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        User user = mapper.getUserByUsernamePassword("admin", "1024");
        System.out.println(user + "\n");
    }

    @Test
    public void testGetUserByMap() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "1024");
        User user = mapper.getUserByMap(map);
        System.out.println(user + "\n");
    }
}
