package com.bronya.manage.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1) // default order by className
@Slf4j
@Component
@Aspect
public class DemoAspect {

    @Pointcut("execution(* com.bronya.manage.service.impl.*.*(..))")
    public void pointcut() {
    }

    @Pointcut("@annotation(com.bronya.manage.aop.demoJoinPoint)")
    public void annotationPointcut() {
    }

    /**
     * fixme                                                                   <br>
     * *  -> any returnType packageName, className, methodName, any 1 argument <br>
     * .. -> any level package, any n argument(s)                              <br>
     */
    @Before("execution(* com.bronya.manage..DeptServiceImpl.*(*)) || execution(* com.bronya.manage..EmpServiceImpl.*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("***** before *****");
        String className = joinPoint.getSignature().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        log.info("className={} methodName={} arguments={}", className, methodName, Arrays.toString(args));
    }

    // @Around("execution(public? returnType packageName.className.methodName(args) throws? Exception?)")
    // e.g. execution(* com.bronya.manage.service.impl.EmpServiceImpl.selectEmpById(int))

    /**
     * fixme                                                                                  <br>
     * 1. @Aspect                                               -> aspect = advice + pointcut <br>
     * 2. selectDeptList method                                 -> joinPoint                  <br>
     * 3. deptServiceImpl instance                              -> target                     <br>
     * 4. @Around @Before @After @AfterReturning @AfterThrowing -> pointcut                   <br>
     * 5. benchmark method                                      -> advice                     <br>
     */

    @Around("pointcut()") // ./DemoAspect.java:23
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();

        // ***** AOP, Aspect Oriented Programming *****
        log.info("********** around begin **********");
        // 1. get target class
        String className = joinPoint.getTarget().getClass().getName();
        log.info("className: {}", className);
        // 2. get joinPoint methodName
        String methodName = joinPoint.getSignature().getName();
        log.info("methodName: {}", methodName);
        Object[] args = joinPoint.getArgs();
        // 3. get joinPoint arguments
        log.info("arguments: {}", Arrays.toString(args));
        // 4. revoke joinPoint method
        Object joinPointResult = joinPoint.proceed(args); // revoke joinPoint method
        log.info("********** around end **********");

        long end = System.currentTimeMillis();
        log.info("{}: {}ms", joinPoint.getSignature().getName(), end - begin);
        return joinPointResult;
    }

    @After("com.bronya.manage.aop.DemoAspect.pointcut()") // ./DemoAspect.java:23
    public void afterAdvice() {
        log.info("***** after *****");
    }

    @AfterReturning("annotationPointcut()")
    public void afterReturningAdvice() {
        log.info("***** afterReturning (../controller/LoginController.java:login) *****");
    }

    @AfterThrowing("execution(* com.bronya.manage..*.*(..))")
    public void afterThrowingAdvice() {
        log.info("***** afterThrowing (throws Exception) *****");
    }
}
