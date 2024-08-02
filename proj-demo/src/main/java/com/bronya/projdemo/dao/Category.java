package com.bronya.projdemo.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @NotNull(groups = Update.class) // validate prop 'id' when 'update'
    private Integer id;
    @NotEmpty(groups = {Insert.class, Update.class}) // validate prop 'categoryName' when 'insert' and 'update'
    private String categoryName;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    public interface Insert {
    }

    public interface Update {
    }
}
