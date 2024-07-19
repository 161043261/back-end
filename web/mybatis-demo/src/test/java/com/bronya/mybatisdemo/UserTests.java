package com.bronya.mybatisdemo;

import com.bronya.mybatisdemo.mapper.UserMapper;
import com.bronya.mybatisdemo.pojo.User;
import com.bronya.mybatisdemo.util.MapperUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// POJO (entity classes) => Mapper (interfaces) => MyBatis proxy => tables
class UserTests {

    @Deprecated
    private static UserMapper getUserMapper() throws IOException {
        // read configuration (pwd = ${mybatis-demo}/src/main/resources)
        InputStream stream = Resources.getResourceAsStream("./mybatis-config.xml");
        // construct a sqlSessionFactoryBuilder
        var builder = new SqlSessionFactoryBuilder();
        // build a sqlSessionFactory
        SqlSessionFactory factory = builder.build(stream);
        // open a sqlSession
        SqlSession session = factory.openSession(true/* autoCommit */); // default false
        // implement the interface UserMapper and initialized an instance userMapper
        return session.getMapper(UserMapper.class);
    }

    @Test
    public void testInsert() throws IOException {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        int rowCount = mapper.insertUser();
        System.out.println(rowCount);
    }

    @Test
    public void testUpdate() throws IOException {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        int rowCount = mapper.updateUser();
        System.out.println(rowCount);
    }

    @Test
    public void testDelete() throws IOException {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        int rowCount = mapper.deleteUser();
        System.out.println(rowCount);
    }

    @Test
    public void testGetUserById() throws IOException {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        User user = mapper.getUserById();
        System.out.println(user);
    }

    @Test
    public void testGetUsers() throws IOException {
        UserMapper mapper = MapperUtil.getMapper(UserMapper.class);
        List<User> users = mapper.getUsers();
        users.forEach(System.out::println);
    }
}
