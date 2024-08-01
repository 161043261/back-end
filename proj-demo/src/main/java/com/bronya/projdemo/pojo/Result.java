package com.bronya.projdemo.pojo;

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

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(0, message, data);
    }

    public static Result<String> error(String message) {
        return new Result<>(1, message, "");
    }
}
