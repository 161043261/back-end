package com.bronya.projdemo.pojo;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;
    // fixme invalid
    @Pattern(regexp = "[A-Za-z0-9]{4,16}")
    private String username;
    // fixme invalid
    @Pattern(regexp = "[A-Za-z0-9]{4,16}")
    private String password;
    private String nickname;
    private String email;
    private String avatar;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
