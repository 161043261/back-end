package com.bronya.projdemo.dao;

import com.bronya.projdemo.annotation.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String image;
    @State
    private Integer state; // state: 0 as BETA, 1 as RELEASE
    @NotNull
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
