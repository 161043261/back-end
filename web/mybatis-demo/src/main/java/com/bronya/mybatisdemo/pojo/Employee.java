package com.bronya.mybatisdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee { // tableName = t_emp
    private Integer empId; // columnName = emp_id
    private String empName; // columnName = emp_name
    private Double empSalary; // columnName = emp_salary
    private Integer empAge; // columnName = emp_age
}
