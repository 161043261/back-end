package com.bronya.mybatisdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // @Data = @Getter + @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User { // tableName = t_user
    private Integer id; // columnName = id
    private String username; // columnName = username
    private String password; // columnName = password
    private Integer age; // columnName = age
    private String sex; // columnName = sex
    private String email; // columnName = email
}
