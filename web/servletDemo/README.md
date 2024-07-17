# Servlet
The key point of Learning Java is to deprecate XML, except Maven.

**Based on Tomcat 10.1.25**

### Test
[HelloServlet](http://127.0.0.1:8080/serve/hello?username=hello&password=1024)

[ServletLifeCycle](http://127.0.0.1:8080/serve/life/cycle)

[ServletConfigTest](http://127.0.0.1:8080/serve/config)

[ServletContextTest](http://127.0.0.1:8080/serve/context)

[ResourcePath](http://127.0.0.1:8080/serve/path)

[HttpServletRequestTest](http://127.0.0.1:8080/serve/request)

[HttpServletResponseTest](http://127.0.0.1:8080/serve/response)

[RequestForward](http://127.0.0.1:8080/serve/forward?username=forward&password=1024)

[ResponseRedirect](http://127.0.0.1:8080/serve/redirect)

[CookieTest](http://127.0.0.1:8080/serve/cookie)

[SessionTest](http://127.0.0.1:8080/serve/session?account=bronya)

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

# WSL
```shell
java -classpath ./target/classes:\
$HOME/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/10.1.25/tomcat-embed-core-10.1.25.jar:\
$HOME/.m2/repository/org/apache/tomcat/tomcat-annotations-api/10.1.25/tomcat-annotations-api-10.1.25.jar:\
$HOME/.m2/repository/org/apache/tomcat/embed/tomcat-embed-jasper/10.1.25/tomcat-embed-jasper-10.1.25.jar:\
$HOME/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/10.1.25/tomcat-embed-el-10.1.25.jar:\
$HOME/.m2/repository/org/eclipse/jdt/ecj/3.33.0/ecj-3.33.0.jar:\
$HOME/.m2/repository/jakarta/servlet/jakarta.servlet-api/6.0.0/jakarta.servlet-api-6.0.0.jar Main
```
