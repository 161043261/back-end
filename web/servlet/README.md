# Servlet
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
│       │   └── com/bronya/servlet/servlets
│       │                          ├── Path.java
│       │                          └── *.java
│       └── webapp      % ** Main.java: final String docBase = new File("./src/main/webapp").getAbsolutePath(); **
│           ├── WEB-INF % ** Path.java: servletContext.getRealPath("./forward"); **
│           │   └── web.xml
│           ├── index.html
│           └── static  % ** Path.java: servletContext.getRealPath("./static"); **
│               └── forward.html
├── target
│   ├── classes
│   │   ├── Main.class
│   │   └── com/bronya/servlet/servlets
│   │                          ├── Path.class
│   │                          └── *.class
│   └── generated-sources
│       └── annotations
└── tomcat.8080
    └── work
        └── Tomcat
            └── localhost
                └── servlet % ** Main.java: Context context = tomcat.addWebapp("/servlet", docBase); **
```