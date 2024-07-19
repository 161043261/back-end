package com.bronya.mybatisdemo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MapperUtil {
    public static <T>T getMapper(Class<T> clazz) {
        T mapper = null;
        try {
            // read configuration (pwd = ${mybatis-demo}/src/main/resources)
            InputStream stream = Resources.getResourceAsStream("./mybatis-config.xml");
            // construct a sqlSessionFactoryBuilder
            var builder = new SqlSessionFactoryBuilder();
            // build a sqlSessionFactory
            SqlSessionFactory factory = builder.build(stream);
            // open a sqlSession
            SqlSession session = factory.openSession(true); // autoCommit = true
            // implement the interface and initialized an instance
            mapper = session.getMapper(clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapper;
    }
}
