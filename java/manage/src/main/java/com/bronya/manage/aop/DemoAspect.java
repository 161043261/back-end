package com.bronya.manage.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class DemoAspect {

    @Before("execution(* com.bronya.manage.service.impl.*.*(..))")
    public void before() {
        log.info("***** before *****");
    }

    // @Around(execution(returnType package.className.methodName(args))")
    // e.g. execution(* com.bronya.manage.service.impl.DeptServiceImpl.selectDeptList())

    /**
     * fixme **********************************************************************************
     * 1. @Aspect                                               -> aspect = advice + pointcut *
     * 2. selectDeptList method                                 -> joinPoint                  *
     * 3. deptServiceImpl instance                              -> target                     *
     * 4. @Around @Before @After @AfterReturning @AfterThrowing -> pointcut                   *
     * 5. benchmark method                                      -> advice                     *
     * ****************************************************************************************
     */

    @Around("execution(* com.bronya.manage.service.impl.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("***** around begin *****");
        long begin = System.currentTimeMillis();
        // AOP, Aspect Oriented Programming
        Object joinPointReturn = joinPoint.proceed(); // revoke joinPoint method
        log.info("***** around end *****");
        long end = System.currentTimeMillis();
        log.info("{}: {}ms", joinPoint.getSignature().getName(), end - begin);
        return joinPointReturn;
    }

    @After("execution(* com.bronya.manage.service.impl.*(..))")
    public void after() {
        log.info("***** after *****");
    }

    @AfterReturning("execution(* com.bronya.manage.service.impl.*(..))")
    public void afterReturning() {
        log.info("***** afterReturning *****");
    }

    @AfterThrowing("execution(* com.bronya.manage.service.impl.*(..))")
    public void afterThrowing() {
        log.info("***** afterThrowing *****");
    }
}
