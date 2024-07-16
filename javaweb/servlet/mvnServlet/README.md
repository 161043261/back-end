# mvn-tomcat
The key point of Learning Java is to deprecate XML, except Maven.

### Requirement
Tomcat 9.x (Tomcat 10.x is NOT supported)

### Test
[Hello](http://127.0.0.1:8080/servlet/hello?username=hello&password=1024)

[ServletConfigTest](http://127.0.0.1:8080/servlet/config)

[ServletContextTest](http://127.0.0.1:8080/servlet/context)

[LifeCycle](http://127.0.0.1:8080/servlet/lifecycle)

[Path](http://127.0.0.1:8080/servlet/path)

[Attribute](http://127.0.0.1:8080/servlet/attribute)

[HttpServletRequestTest](http://127.0.0.1:8080/servlet/req)

[HttpServletResponseTest](http://127.0.0.1:8080/servlet/resp)

[Forward](http://127.0.0.1:8080/servlet/forward?username=forward&password=2048)

[Redirect](http://127.0.0.1:8080/servlet/redirect)

### Tree
```tex
.
├── README.md
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   ├── Main.java
│       │   └── com
│       │       └── bronya
│       │           └── mvnTomcat
│       │               └── servlet
│       │                   └── *.java
│       └── webapp % servletContext.getRealPath("./");
│           ├── WEB-INF % servletContext.getRealPath("./WEB-INF");
│           │   └── web.xml
│           └── index.html
├── target
│   ├── classes
│   │   ├── Main.class
│   │   └── com
│   │       └── bronya
│   │           └── mvnTomcat
│   │               └── servlet
│   │                   └── *.class
│   ├── mvnServlet
│   │   ├── META-INF
│   │   ├── WEB-INF
│   │   │   ├── classes
│   │   │   │   ├── Main.class
│   │   │   │   └── com
│   │   │   │       └── bronya
│   │   │   │           └── mvnTomcat
│   │   │   │               └── servlet
│   │   │   │                   └── *.class
│   │   │   ├── lib
│   │   │   │   └── *.jar
│   │   │   └── web.xml
│   │   └── index.html
│   └── mvnServlet.war
└── tomcat.8080
    └── work
        └── Tomcat
            └── localhost
                ├── ROOT
                └── servlet % contextPath
```