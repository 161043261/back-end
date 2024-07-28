package com.bronya.manage.exception;

import com.bronya.manage.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        log.error(e.getMessage());
        return Result.error("Fatal Error");
    }
}
