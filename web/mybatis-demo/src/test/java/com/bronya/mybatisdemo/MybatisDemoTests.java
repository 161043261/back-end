package com.bronya.mybatisdemo;

import com.bronya.mybatisdemo.mapper.UserMapper;
import com.bronya.mybatisdemo.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

// POJO (entity classes) => Mapper (mybatis proxies interface) => tables
class MybatisDemoTests {

    @Test
    public void testInsert() throws IOException {
        // read configuration (pwd = ${mybatis-demo}/src/main/resources)
        InputStream stream = Resources.getResourceAsStream("./mybatis-config.xml");
        // construct a sql  SessionFactoryBuilder
        var builder = new SqlSessionFactoryBuilder();
        // build a sqlSessionFactory
        SqlSessionFactory factory = builder.build(stream);
        // open a sqlSession
        SqlSession session = factory.openSession(false); // autoCommit = false
        // implement the interface UserMapper and initialized an instance userMapper
        UserMapper mapper = session.getMapper(UserMapper.class); // proxy
        int rowCount = mapper.insertUser();
        session.commit();
        System.out.println(rowCount);
    }

    @Test
    public void testUpdate() throws IOException {
        InputStream stream = Resources.getResourceAsStream("./mybatis-config.xml");
        var builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession session = factory.openSession(true); // autoC
        UserMapper mapper = session.getMapper(UserMapper.class);
        int rowCount = mapper.updateUser();
        System.out.println(rowCount);
    }

    @Test
    public void testDelete() throws IOException {
        InputStream stream = Resources.getResourceAsStream("./mybatis-config.xml");
        var builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession session = factory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        int rouCount = mapper.deleteUser();
        System.out.println(rouCount);
    }

    @Test
    public void testGetUserById() throws IOException {
        InputStream stream = Resources.getResourceAsStream("./mybatis-config.xml");
        var builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession session = factory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.getUserById();
        System.out.println(user);
    }

    @Test
    public void testGetUsers() throws IOException {
        InputStream stream = Resources.getResourceAsStream("./mybatis-config.xml");
        var builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(stream);
        SqlSession session = factory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.getUsers();
        users.forEach(System.out::println);
    }
}
