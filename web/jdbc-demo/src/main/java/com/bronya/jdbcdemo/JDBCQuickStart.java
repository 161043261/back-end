package com.bronya.jdbcdemo;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class JDBCQuickStart {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String qualifiedName = Driver.class.getName();
        System.out.println(qualifiedName); // com.mysql.cj.jdbc.Driver

        // Get Connection
        String url = "jdbc:mysql://localhost:3306/bronya";
        String user = "root";
        String password = "0228";
        Connection connection = DriverManager.getConnection(url, user, password);

        // Create Statement to Execute Query
        Statement statement = connection.createStatement();
        final String sql = "SELECT emp_id, emp_name, emp_salary, emp_age FROM t_emp";
        ResultSet resultSet = statement.executeQuery(sql);

        // Iterate the resultSet
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }

        // Close Resource in Reverse Order (optional)
        resultSet.close();
        statement.close();
        connection.close();
    }
}
