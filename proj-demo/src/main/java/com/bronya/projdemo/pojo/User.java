package com.bronya.projdemo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Pattern(regexp = "^\\S{4,16}$")
    private String username;

    @Pattern(regexp = "^\\S{4,16}$")
    @JsonIgnore
    private String password;

    @NotEmpty // name != null && name != ""
    private String name;

    @NotEmpty
    @Email
    private String email;

    private String avatar;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
