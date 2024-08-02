package com.bronya.projdemo.exception;

import com.bronya.projdemo.dao.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler { // global

    @ExceptionHandler(Exception.class)
    public Result<String> handler(Exception e) {
        e.printStackTrace();
        log.error(e.getMessage());
        return Result.err(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "Fatal Error");
    }
}
