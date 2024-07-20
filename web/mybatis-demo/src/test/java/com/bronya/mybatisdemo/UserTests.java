package com.bronya.mybatisdemo;

import com.bronya.mybatisdemo.mapper.UserMapper;
import com.bronya.mybatisdemo.pojo.User;
import com.bronya.mybatisdemo.util.MapperUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bronya.mybatisdemo.util.Colors.GREEN;
import static com.bronya.mybatisdemo.util.Colors.RESET;

// pojo/* (entity classes)
// -> mapper/* (mapping interfaces)
// -> MyBatis proxy -> mapping classes -> tables
class UserTests {

    @Test
    public void testGetUserList() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        List<User> users = mapper.getUserList();
        // users.forEach(System.out::println);
        for (User user : users) {
            System.out.println(GREEN + user + RESET);
        }
    }

    @Test
    public void testGetUserByUsername() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        User admin = mapper.getUserByUsername("admin");
        System.out.println(GREEN + admin + RESET);
    }

    @Test
    public void testGetUserByUsernamePassword() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        List<User> users = mapper.getUserByUsernamePassword("admin", "1024");
        for (User user : users) {
            System.out.println(GREEN + user + RESET);
        }
    }

    @Test
    public void testGetUserByMap() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "admin");
        map.put("password", "1024");
        User user = mapper.getUserByMap(map);
        System.out.println(GREEN + user + RESET);
    }

    @Test
    public void testInsertUser() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        User user = new User(null, "Tom", "1234", 3, "male", "tom@bronya.com");
        int rowCount = mapper.insertUser(user);
        System.out.println(GREEN + "rowCount = " + rowCount + RESET);
    }

    @Test
    public void testGetUserByParam() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        User user = mapper.getUserByParam("admin", "1024");
        System.out.println(GREEN + user + RESET);
    }

    @Test
    public void testUserCount() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        int userCount = mapper.getUserCount();
        System.out.println(GREEN + "userCount = " + userCount + RESET);
    }

    @Test
    public void testGetMapById() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        Map<String, Object> map = mapper.getMapById(1);
        System.out.println(GREEN + map + RESET);
    }

    @Test
    public void testGetMapList() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        List<Map<String, Object>> mapList = mapper.getMapList();
        for (Map<String, Object> map : mapList) {
            System.out.println(GREEN + map + RESET);
        }
    }

    @Test
    public void testGetMap() { // key = user.id
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        Map<Integer, Object> map = mapper.getMap();
        System.out.println(GREEN + map + RESET);
        // {1={password=1024, sex=male, id=1, age=1, email=admin@bronya.com, username=admin}, ...}
    }

    @Test
    public void testFuzzyQuery() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        List<User> users = mapper.fuzzyQuery("o");
        for (User user : users) {
            System.out.println(GREEN + user + RESET);
        }
    }

    @Test
    public void testBatchDelete() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        int rowCount = mapper.batchDelete("2, 3");
        System.out.println(GREEN + "rowCount = " + rowCount + RESET);
    }

    @Test
    public void testDynamicTableName() {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        List<User> users = mapper.dynamicTableName("t_user");
        for (User user : users) {
            System.out.println(GREEN + user + RESET);
        }
    }

    @Test
    public void testPrimaryKeyRetrieval() {
        User user = new User(null, "Jerry", "5678", 4, "male", "jerry@bronya.com");
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        mapper.primaryKeyRetrieval(user);
        // Primary Key Retrieval
        System.out.println(GREEN + "generatedKey = " + user.getId() + RESET);
    }
}
