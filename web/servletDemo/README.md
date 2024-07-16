# Servlet
The key point of Learning Java is to deprecate XML, except Maven.

### Requirement
Tomcat 9.x (Tomcat 10.x is NOT supported)

### Test
[Hello](http://127.0.0.1:8080/serve/hello?username=hello&password=1024)

[ServletConfigTest](http://127.0.0.1:8080/serve/config)

[ServletContextTest](http://127.0.0.1:8080/serve/context)

[LifeCycle](http://127.0.0.1:8080/serve/lifecycle)

[Path](http://127.0.0.1:8080/serve/path)

[Attribute](http://127.0.0.1:8080/serve/attribute)

[HttpServletRequestTest](http://127.0.0.1:8080/serve/req)

[HttpServletResponseTest](http://127.0.0.1:8080/serve/resp)

[Forward](http://127.0.0.1:8080/serve/forward?username=forward&password=2048)

[Redirect](http://127.0.0.1:8080/serve/redirect)

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
│       └── webapp % Main.java: final String docBase = new File("./src/main/webapp").getAbsolutePath();
│           ├── WEB-INF % Path.java: servletContext.getRealPath("./WEB-INF");
│           │   └── web.xml
│           ├── index.html
│           └── static % Path.java: servletContext.getRealPath("./static");
│               └── forward.html
├── target
│   ├── classes % Main.java: final String base = new File("./target/classes").getAbsolutePath();
│   │   ├── Main.class
│   │   └── com/bronya/servlet/servlets
│   │                          ├── Path.class
│   │                          └── *.class
│   ├── servletDemo
│   │   ├── META-INF
│   │   ├── WEB-INF
│   │   │   ├── classes % Main.java: final String webAppMount = "/WEB-INF/classes";
│   │   │   │   ├── Main.class
│   │   │   │   └── com/bronya/servlet/servlets
│   │   │   │                          ├── Path.class
│   │   │   │                          └── *.class
│   │   │   ├── lib
│   │   │   │   └── *.jar
│   │   │   └── web.xml
│   │   ├── index.html
│   │   └── static
│   │       └── forward.html
│   └── servletDemo.war
└── tomcat.8080
    └── work
        └── Tomcat
            └── localhost
                └── serve % Main.java: Context context = tomcat.addWebapp("/serve", docBase);
```
