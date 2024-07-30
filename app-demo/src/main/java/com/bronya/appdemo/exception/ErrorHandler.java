package com.bronya.appdemo.exception;

import com.bronya.appdemo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler { // global

    @ExceptionHandler(Exception.class)
    public Result handler(Exception e) {
        log.error(e.getMessage());
        return Result.error("Fatal Error");
    }
}
