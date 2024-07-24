package com.bronya.jdbcdemo.pojo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
/* t_emp
  +--------+----------+------------+---------+
  | emp_id | emp_name | emp_salary | emp_age |
  +--------+----------+------------+---------+
  |      1 | Klee     |  111.10000 |       1 |
  +--------+----------+------------+---------+
 */ public class Employee { // t_emp
    private Integer empId; // emp_id
    private String empName; // emp_name
    private Double empSalary; // emp_salary
    private Integer empAge; // emp_age
}
