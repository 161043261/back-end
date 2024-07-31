package com.bronya.projdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code; // 0 as succeed, 1 as fail
    private String message;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(0, "ok", data);
    }

    // public static Result<String> success() {
    //     return new Result<>(0, "ok", "");
    // }

    public static Result<String> error(String message) {
        return new Result<>(1, message, "");
    }
}
