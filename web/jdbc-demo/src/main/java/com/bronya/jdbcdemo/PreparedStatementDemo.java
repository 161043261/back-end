package com.bronya.jdbcdemo;

import java.sql.*;
import java.util.Scanner;

/**
 * Use PreparedStatement to Prevent SQL Injection
 */
public class PreparedStatementDemo {

    public static void main(String[] args) throws SQLException {
        // get connection
        String url = "jdbc:mysql://localhost:3306/bronya";
        String user = "root";
        String password = "0228";
        Connection connection = DriverManager.getConnection(url, user, password);

        // create preparedStatement to execute query
        String sql = "SELECT emp_id, emp_salary, emp_age FROM t_emp WHERE emp_name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        Scanner in = new Scanner(System.in);

        // replace the ? placeholder with string
        String empName = in.nextLine();
        // parameterIndex starts from 1
        preparedStatement.setString(1/* parameterIndex */, empName);
        ResultSet resultSet = preparedStatement.executeQuery();

        // iterate the resultSet
        while (resultSet.next()) {
            int empId = resultSet.getInt("emp_id");
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");
            System.out.println(empName + ": " + empId + "\t" + empSalary + "\t" + empAge);
        }
    }
}
