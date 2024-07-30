package com.bronya.appdemo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept { // dept
    private Integer id; // id
    private String name; // name
    private LocalDateTime createTime; // create_time
    private LocalDateTime updateTime; // update_time
}
