package com.bronya.jdbcdemo;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Random;

import static com.bronya.jdbcdemo.Colors.*;

public class TestJdbc {

    @Test
    public void testQueryValue() throws SQLException {
        // regular
        System.out.println(GREEN + "testQueryValue" + RESET);
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya"/* url */, "root"/* user */, "0228"/* password */);
        String sql = "select count(*) as count from t_emp";
        // Use PreparedStatement to prevent sql injection
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) { // for (row : rows)
            System.out.println(resultSet.getInt("count"/* columnLabel */));
            System.out.println(resultSet.getInt(1/* columnIndex */));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }


    @Test
    public void testQueryRow() throws SQLException {
        // bold
        System.out.println(GREEN_BD + "testQueryRow" + RESET);
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "select emp_name, emp_salary, emp_age from t_emp where emp_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        int empId = new Random().nextInt(1, 5);
        System.out.println("emp_id = " + empId);
        statement.setInt(1, empId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + " -> " + empName + "\t" + empSalary + "\t" + empAge);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testQueryRows() throws SQLException {
        // bold bright
        System.out.println(GREEN_BD_BR + "testQueryRows" + RESET);
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "select * from t_emp where emp_age > ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        int minEmpAge = new Random().nextInt(1, 5);
        System.out.println("emp_age > " + minEmpAge);
        statement.setInt(1, minEmpAge);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testInsert() throws SQLException {
        // background
        System.out.println(GREEN_BG + "testInsert" + RESET);
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "insert into t_emp (emp_name, emp_salary, emp_age) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Nilou"); // emp_name
        statement.setDouble(2, 555.5); // emp_salary
        statement.setInt(3, 5); // emp_age
        int rowCount = statement.executeUpdate(); // number of rows affected
        System.out.println(rowCount == 1 ? "ok" : "err");
        statement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws SQLException {
        // background bright
        System.out.println(GREEN_BG_BR + "testUpdate" + RESET);
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "update t_emp set emp_salary = ? where emp_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        int empId = new Random().nextInt(1, 5);
        System.out.println("emp_id = " + empId);
        statement.setDouble(1, 888.8);
        statement.setInt(2, empId);
        int rowCount = statement.executeUpdate();
        System.out.println(rowCount == 1 ? "ok" : "err");
        statement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws SQLException {
        // underlined
        System.out.println(GREEN_UL + "testDelete" + RESET);
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "delete from t_emp where emp_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        int empId = new Random().nextInt(1, 5);
        System.out.println("emp_id = " + empId);
        statement.setInt(1, empId);
        int rowCount = statement.executeUpdate();
        System.out.println(rowCount == 1 ? "ok" : "err");
        statement.close();
        connection.close();
    }
}
