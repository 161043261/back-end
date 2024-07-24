# boot-demo

### DemoController

[emp](http://127.0.1:8080/emp?name=tom&age=22)

[str](http://127.0.1:8080/str?name=tom&age=22)

[orm](http://127.0.0.1:8080/orm?name=tom&age=22)

[obj](http://127.0.0.1:8080/obj?name=tom&age=22&address.province=jiangsu&address.city=nanjing)

[box](http://127.0.0.1:8080/box?like=sing&like=dance&like=rap&like=basketball)

[boxList](http://127.0.0.1:8080/box/list?city=nanjing&city=suzhou&city=wuxi)

[date](http://127.0.0.1:8080/date?date=2002-02-28)

[path](http://127.0.0.1:8080/path/tom/22)

[json](http://127.0.0.1:8080/json) (use postman)

```json
{
  "name": "tom",
  "age": 22,
  "address": {
    "province": "jiangsu",
    "city": "nanjing"
  }
}
```

**Wrapped by Result**

[empRes](http://127.0.1:8080/emp/res?name=tom&age=22)

[objRes](http://127.0.0.1:8080/obj/res?name=tom&age=22&address.province=jiangsu&address.city=nanjing)

[boxListRes](http://127.0.0.1:8080/box/list/res?city=nanjing&city=suzhou&city=wuxi)

## IOC

IOC, Inversion Of Control

>   Spring IoC (Inversion of Control) Container is the core of Spring Framework. It creates the objects, configures and assembles their dependencies, manages their entire life cycle. The Container uses Dependency Injection(DI) to manage the components that make up the application. It gets the information about the objects from a configuration file(XML) or Java Code or Java Annotations and Java POJO class. These objects are called Beans. Since the Controlling of Java objects and their lifecycle is not done by the developers, hence the name Inversion Of Control. The followings are some of the main features of Spring IoC.

-   Creating Object for us
-   Managing our objects
-   Helping our application to be configurable
-   Managing dependencies

## DI

DI, Dependency Injection

>   Dependency Injection is the main functionality provided by Spring IOC(Inversion of Control). The Spring-Core module is responsible for injecting dependencies through either Constructor or Setter methods. The design principle of Inversion of Control emphasizes keeping the Java classes independent of each other and the container frees them from object creation and maintenance. These classes, managed by Spring, must adhere to the standard definition of Java-Bean. Dependency Injection in Spring also ensures loose coupling between the classes. There are two types of Spring Dependency Injection.

1.  Setter Dependency Injection (SDI)
2.  Constructor Dependency Injection (CDI)
