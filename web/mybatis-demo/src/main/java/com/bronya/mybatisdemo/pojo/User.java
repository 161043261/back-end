package com.bronya.mybatisdemo.pojo;

import lombok.*;

@Data // @Getter + @Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String sex;
    private String email;
}
