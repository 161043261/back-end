package com.bronya.appdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Emp { // emp
    private Integer id; // id
    private String username; // username
    private String password; // password
    private String name; // name
    private Short gender; // gender
    private String image; // image
    private Short job; // job
    private LocalDate entrydate; // entrydate
    private Integer deptId; // dept_id
    private LocalDateTime createTime; // create_time
    private LocalDateTime updateTime; // update_time
}
