package com.bronya.mybatisdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp { // tableName = t_emp
    private Integer eid; // columnName = eid
    private String empName; // columnName = emp_name
    private Integer age; // columnName = age
    private String sex; // columnName = sex
    private String email; // columnName = email
    private Dept dept; // columnName = did
}
