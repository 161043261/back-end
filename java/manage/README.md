# manage system

### JWT, JSON Web Token

Headers.Payload.Signature

### interceptor

```tex
-> request -> preFilterLogic -> filter.doFilter() -> dispatcherServlet -> interceptor.preHandle() -> controller -->*
                                                                                                                   |
<- postFilterLogic <- dispatcherServlet <- interceptor.afterCompletion() <- interceptor.postHandle() <- response <-*
```
