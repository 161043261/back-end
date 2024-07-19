package com.bronya.mybatisdemo;

import com.bronya.mybatisdemo.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

// POJO (entity classes) => Mapper (mybatis proxies interface) => tables
class MybatisDemoTests {

    @Test
    public void testInsert() throws IOException {
        // read configuration (pwd = ${mybatis-demo}/src/main/resources)
        InputStream stream = Resources.getResourceAsStream("./mybatis-config.xml");
        // construct a sqlSessionFactoryBuilder
        var builder = new SqlSessionFactoryBuilder();
        // build a sqlSessionFactory
        SqlSessionFactory factory = builder.build(stream);
        // open a sqlSession
        SqlSession session = factory.openSession(false/* autoCommit = false */);
        // implement the interface UserMapper and initialized an instance userMapper
        UserMapper mapper = session.getMapper(UserMapper.class); // proxy
        int rowCount = mapper.insertUser();
        session.commit();
        System.out.println(rowCount);
    }
}
