package com.bronya.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import com.bronya.jdbcdemo.pojo.Employee;

public class TestJdbc {

    @Test
    public void testQueryValue() throws SQLException {
        System.out.println("test Querying a Value");
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
        System.out.println("Test Querying a Row");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "select emp_name, emp_salary, emp_age from t_emp where emp_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        int empId = new Random().nextInt(1, 5);
        System.out.println("emp_id = " + empId);
        statement.setInt(1, empId);
        ResultSet resultSet = statement.executeQuery();
        List<Employee> empList = new ArrayList<>();
        while (resultSet.next()) {
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            Employee employee = new Employee();
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
            empList.add(employee);
            // System.out.println(empId + " -> " + empName + "\t" + empSalary + "\t" + empAge);
        }
        empList.forEach((employee) -> {
            System.out.println(employee.toString());
        });
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testQueryMultiRows() throws SQLException {
        // bold bright
        System.out.println("Test Querying a Rows");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "select * from t_emp where emp_age > ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        int minEmpAge = new Random().nextInt(1, 5);
        System.out.println("emp_age > " + minEmpAge);
        statement.setInt(1, minEmpAge);
        ResultSet resultSet = statement.executeQuery();
        List<Employee> empList = new ArrayList<Employee>();
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            Employee employee = new Employee();
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);
            empList.add(employee);
            // System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }
        empList.forEach((employee) -> {
            System.out.println(employee.toString());
        });
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testInsert() throws SQLException {
        // background
        System.out.println("Test a Insert Operation");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "insert into t_emp (emp_name, emp_salary, emp_age) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS/* 1 */); // Magic Constant

        // Primary Key Retrieval
        Employee employee = new Employee(null, "Nilou", 555.5, 5);
        statement.setString(1, employee.getEmpName()); // emp_name
        statement.setDouble(2, employee.getEmpSalary()); // emp_salary
        statement.setInt(3, employee.getEmpAge()); // emp_age

        int rowCount = statement.executeUpdate(); // number of rows affected
        System.out.println(rowCount > 0 ? "ok" : "err");

        ResultSet generatedKeys = null;
        if (rowCount > 0) {
            System.out.println("Before Primary Key Retrieval: " + employee);
            generatedKeys = statement.getGeneratedKeys(); // the generatedKeys is 1 row, 1 column
            while (generatedKeys.next()) {
                int empId = generatedKeys.getInt(1);
                System.out.println("GeneratedKey: emp_id=" + empId);
                employee.setEmpId(empId);
            }
            System.out.println("After Primary Key Retrieval: " + employee);
        }
        if (generatedKeys != null) generatedKeys.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testUpdate() throws SQLException {
        // background bright
        System.out.println("Test an Update Operation");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "update t_emp set emp_salary = ? where emp_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        int empId = new Random().nextInt(1, 5);
        System.out.println("emp_id = " + empId);
        statement.setDouble(1, 888.8);
        statement.setInt(2, empId);
        int rowCount = statement.executeUpdate(); // number of rows affected
        System.out.println(rowCount > 0 ? "ok" : "err");
        statement.close();
        connection.close();
    }

    @Test
    public void testDelete() throws SQLException {
        // underlined
        System.out.println("Test a Delete Operation");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "delete from t_emp where emp_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        int empId = new Random().nextInt(1, 5);
        System.out.println("emp_id = " + empId);
        statement.setInt(1, empId);
        int rowCount = statement.executeUpdate(); // number of rows affected
        System.out.println(rowCount > 0 ? "ok" : "err");
        statement.close();
        connection.close();
    }

    @Test
    @Deprecated
    public void testMultiInsert() throws SQLException {
        // background
        System.out.println("Test Multiple Insert Operation");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya", "root", "0228");
        String sql = "insert into t_emp (emp_name, emp_salary, emp_age) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        // benchmark
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            statement.setString(1, "Nilou" + i); // emp_name
            statement.setDouble(2, 555.5 + i); // emp_salary
            statement.setInt(3, 5 + i); // emp_age
            statement.executeUpdate();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Total " + (endTime - startTime) + "ms"); // Total 12238
        statement.close();
        connection.close();
    }

    /*
      1. url = jdbc:mysql:///bronya?rewriteBatchedStatements=true
      2. value × values √
      3. No trailing ';'
      4. statement.addBatch();
      5. statement.executeBatch();
     */
    @Test
    public void testBatchInsert() throws SQLException {
        System.out.println("Test Batch Insert Operation");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///bronya?rewriteBatchedStatements=true", // batch operation
                "root", "0228");
        String sql = "insert into t_emp (emp_name, emp_salary, emp_age) values (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        // benchmark
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_000; i++) {
            statement.setString(1, "Ayaka" + i); // emp_name
            statement.setDouble(2, 666.6 + i); // emp_salary
            statement.setInt(3, 6 + i); // emp_age
            statement.addBatch(); // concat SQL statements
        }
        statement.executeBatch();
        long endTime = System.currentTimeMillis();
        System.out.println("Total " + (endTime - startTime) + "ms"); // Total 182ms
        statement.close();
        connection.close();
    }
}
