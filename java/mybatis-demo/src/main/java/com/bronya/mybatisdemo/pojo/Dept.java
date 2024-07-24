package com.bronya.mybatisdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept { // tableName = t_dept
    private Integer did; // columnName = did
    private String deptName; // columnName = dept_name
    private List<Emp> empList;
}
