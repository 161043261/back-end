package com.bronya.appdemo.aop;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bronya.appdemo.mapper.OperateLogMapper;
import com.bronya.appdemo.pojo.OperateLog;
import com.bronya.appdemo.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Aspect
public class LogAspect {

    private OperateLogMapper operateLogMapper;
    private HttpServletRequest req;

    @Autowired
    public void setOperateLogMapper(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    @Autowired
    public void setHttpServletRequest(HttpServletRequest req) {
        this.req = req;
    }

    @Around("@annotation(com.bronya.appdemo.annotation.LogAnnotation)")
    public Object logAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String jwtString = req.getHeader("token");
        Claims claims = JwtUtils.parseJwtString(jwtString);
        Integer operateUser = (Integer) claims.get("id");
        LocalDateTime operateTime = LocalDateTime.now();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String args = Arrays.deepToString(joinPoint.getArgs());
        long begin = System.currentTimeMillis();
        Object retValue = joinPoint.proceed();
        long end = System.currentTimeMillis();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String returnValue = ow.writeValueAsString(retValue);
        Long benchmarkTime = end - begin;
        var operateLog = new OperateLog(null, operateUser, operateTime, className, methodName, args, returnValue, benchmarkTime);
        int rowCount = operateLogMapper.insertOperateLog(operateLog);
        log.info("rowCount={}", rowCount);
        return retValue;
    }
}
