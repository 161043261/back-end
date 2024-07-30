# A Demo Application

### JWT, JSON Web Token

Headers.Payload.Signature

### interceptor

```tex
-> request -> preFilterLogic -> filter.doFilter() -> dispatcherServlet -> interceptor.preHandle() -> controller -->*
                                                                                                                   |
<- postFilterLogic <- dispatcherServlet <- interceptor.afterCompletion() <- interceptor.postHandle() <- response <-*
```

### AOP, Aspect Oriented Programming

```tex
1. @Aspect                                               -> aspect = advice + pointcut
2. selectDeptList method                                 -> joinPoint
3. deptServiceImpl instance                              -> target
4. @Around @Before @After @AfterReturning @AfterThrowing -> pointcut
5. benchmark method                                      -> advice
```
