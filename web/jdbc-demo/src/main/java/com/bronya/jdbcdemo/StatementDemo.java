package com.bronya.jdbcdemo;

import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class StatementDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String qualifiedName = Driver.class.getName();
        System.out.println(qualifiedName); // com.mysql.cj.jdbc.Driver

        // get connection
        String url = "jdbc:mysql://localhost:3306/bronya";
        String user = "root";
        String password = "0228";
        Connection connection = DriverManager.getConnection(url, user, password);

        // create statement to execute query
        final String sql = "SELECT emp_id, emp_name, emp_salary, emp_age FROM t_emp";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // iterate the resultSet
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            String empName = resultSet.getString("emp_name");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empId + "\t" + empName + "\t" + empSalary + "\t" + empAge);
        }

        // close resource in reverse order (optional)
        resultSet.close();
        statement.close();
        connection.close();
    }
}
