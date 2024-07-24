package com.bronya.jdbcdemo;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class TestHikari {
    @Test
    public void testHardCoding() {
        // try with resources
        try (HikariDataSource dataSource = new HikariDataSource()) {
            // necessary configurations
            dataSource.setDriverClassName(Driver.class.getName());
            dataSource.setJdbcUrl("jdbc:mysql:///bronya"); // "localhost:3306" is omitted
            dataSource.setUsername("root");
            dataSource.setPassword("0228");
            // unnecessary configurations
            dataSource.setMinimumIdle(1); // connectionsNum >= 1
            dataSource.setMaximumPoolSize(10); // connectionsNum <= 10
            // get connection
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            // close connection
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testSoftCoding() {
        // pwd = ${jdbc-demo}
        HikariConfig config = new HikariConfig("./src/main/resources/hikari.properties");
        // try with resources
        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testHikari() throws IOException {
        Properties properties = new Properties();
        // pwd = ${jdbc-demo}/src/main/resources
        InputStream inputStream = TestHikari.class.getClassLoader().getResourceAsStream("./hikari.properties");
        properties.load(inputStream);
        HikariConfig config = new HikariConfig(properties);
        // try with resources
        try (HikariDataSource dataSource = new HikariDataSource(config)) {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
