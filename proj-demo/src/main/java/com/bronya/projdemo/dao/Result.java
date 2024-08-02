package com.bronya.projdemo.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code; // 0 as successful, 1 as failed
    private String message;
    private T data;

    public static <T> Result<T> ok(String message, T data) {
        return new Result<>(1, message, data); // 1 as ok
    }

    public static Result<String> err(String message) {
        return new Result<>(0, message, ""); // 0 as error
    }
}
