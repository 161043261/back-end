package com.bronya.projdemo.exception;

import com.bronya.projdemo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler { // global

    @ExceptionHandler(Exception.class)
    public Result<String> handler(Exception e) {
        log.error(e.getMessage());
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "FATAL ERROR");
    }
}
