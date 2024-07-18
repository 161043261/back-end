package com.bronya.jdbcdemo;

import org.junit.Test;

import java.sql.*;

public class TestQuery {

    @Test
    public void testQueryValue() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", // url
                "root", // user
                "0228"); // password
        String sql = "select count(*) as count from t_emp";
        // Use PreparedStatement to prevent sql injection
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) { // for (row : rows)
            System.out.println(resultSet.getInt("count"/* columnLabel */));
            System.out.println(resultSet.getInt(1/* columnIndex */));
        }
    }
}
