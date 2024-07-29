package com.bronya.manage.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class TimeAspect {
    // @Around(execution(returnType package.className.methodName(args))")
    // e.g. execution(* com.bronya.manage.service.DeptServiceImpl.selectDeptList())

    /**
     * fixme **********************************************************************************
     * 1. @Aspect                                               <- aspect = advice + pointcut *
     * 2. selectDeptList method                                 <- joinPoint                  *
     * 3. deptServiceImpl instance                              <- target                     *
     * 4. @Around @Before @After @AfterReturning @AfterThrowing <- pointcut                   *
     * 5. benchmark method                                      <- advice                     *
     * ****************************************************************************************
     */

    @Around("execution(* com.bronya.manage.service.*.*(..))")
    public Object benchmark(ProceedingJoinPoint joinPoint) throws Throwable {
        // begin
        long begin = System.currentTimeMillis();
        // AOP, Aspect Oriented Programming
        Object joinPointReturn = joinPoint.proceed(); // revoke joinPoint method
        // end
        long end = System.currentTimeMillis();
        log.info("{}: {}ms", joinPoint.getSignature().getName(), end - begin);
        return joinPointReturn;
    }
}
