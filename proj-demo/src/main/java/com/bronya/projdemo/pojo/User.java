package com.bronya.projdemo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
// @Valid // enable validation
public class User {
    @NotNull // id != null
    private Integer id;

    private String username;

    // @JsonIgnore // let json string does NOT contain password
    @JsonIgnoreProperties
    private String password;

    @NotEmpty // nickname != null && nickname != ""
    @Pattern(regexp = "^\\S{4,16}$")
    private String nickname;

    @NotEmpty
    @Email
    private String email;

    private String avatar;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
