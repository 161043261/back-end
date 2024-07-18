package com.bronya.jdbcdemo;

import com.bronya.jdbcdemo.pojo.Employee;

import java.sql.*;
import java.util.Scanner;

/**
 * Use PreparedStatement to prevent
 * <a href="https://en.wikipedia.org/wiki/SQL_injection">SQL Injection</a>
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
        System.out.print("emp_name = ");
        String empName = in.nextLine();
        // replace the ? placeholder with string
        preparedStatement.setString(1, empName); // the first parameterIndex is 1
        ResultSet resultSet = preparedStatement.executeQuery();

        // Object Relational Mapping (ORM)
        Employee employee = new Employee();

        // iterate the resultSet
        while (resultSet.next()) { // Moves the cursor forward one row from its current position.
            int empId = resultSet.getInt("emp_id"/* columnLabel */);
            double empSalary = resultSet.getDouble("emp_salary");
            int empAge = resultSet.getInt("emp_age");

            // Object Relational Mapping (ORM)
            employee.setEmpId(empId);
            employee.setEmpName(empName);
            employee.setEmpSalary(empSalary);
            employee.setEmpAge(empAge);

            // System.out.println(empName + " -> " + empId + "\t" + empSalary + "\t" + empAge);
            System.out.println(employee.toString());
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
