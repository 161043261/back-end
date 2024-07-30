package com.bronya.mybatisdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.bronya.mybatisdemo.mapper.PageMapper;
import com.bronya.mybatisdemo.pojo.Employee;
import com.bronya.mybatisdemo.util.MapperUtil;

public class PageTests {
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

    @Test
    public void testGetEmployeeListByPage() {
        PageMapper mapper = MapperUtil.getMapper(PageMapper.class);
        List<Employee> employeeList = mapper.getEmployeeListByPage(0, 3);
        System.out.println(employeeList);
    }
}
