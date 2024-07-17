# Servlet

The key point of Learning Java is to deprecate XML, except Maven.

**Based on Tomcat 10.1.25**

### Servlet Demo

[HelloServlet](http://127.0.0.1:8080/demo/hello?username=hello&password=1024)

[ServletLifeCycle](http://127.0.0.1:8080/demo/life/cycle)

[ServletConfigDemo](http://127.0.0.1:8080/demo/config)

[ServletContextDemo](http://127.0.0.1:8080/demo/context)

[ResourcePath](http://127.0.0.1:8080/demo/resource/path)

[HttpServletRequestDemo](http://127.0.0.1:8080/demo/request)

[HttpServletResponseDemo](http://127.0.0.1:8080/demo/response)

[RequestForward](http://127.0.0.1:8080/demo/forward?username=forward&password=1024)

[ResponseRedirect](http://127.0.0.1:8080/demo/redirect)

[CookieDemo](http://127.0.0.1:8080/demo/cookie)

[SessionDemo](http://127.0.0.1:8080/demo/session?company=bronya)

### Listeners Demo

[listeners/ServletContextInsert](http://127.0.0.1:8080/demo/context/insert)

[listeners/ServletContextUpdate](http://127.0.0.1:8080/demo/context/update)

[listeners/ServletContextDelete](http://127.0.0.1:8080/demo/context/delete)

[listeners/TestServlet](http://127.0.0.1:8080/demo/test)

### Tree .

```tex
.
├── README.md
├── pom.xml
├── src
│   └── main
│       ├── java
│       │   ├── Main.java
│       │   └── com/bronya/servlet/servlets
│       │                          ├── ResourcePath.java
│       │                          └── *.java
│       └── webapp % Main.java: final String docBase = new File("./src/main/webapp").getAbsolutePath();
│           ├── WEB-INF % ResourcePath.java: servletContext.getRealPath("./WEB-INF");
│           │   └── web.xml
│           ├── index.html
│           └── static % ResourcePath.java: servletContext.getRealPath("./static");
│               └── forward.html
├── target
│   ├── classes % Main.java: final String base = new File("./target/classes").getAbsolutePath();
│   │   ├── Main.class
│   │   └── com/bronya/servlet/servlets
│   │                          ├── ResourcePath.class
│   │                          └── *.class
│   ├── servletDemo
│   │   ├── META-INF
│   │   ├── WEB-INF
│   │   │   ├── classes % Main.java: final String webAppMount = "/WEB-INF/classes";
│   │   │   │   ├── Main.class
│   │   │   │   └── com/bronya/servlet/servlets
│   │   │   │                          ├── ResourcePath.class
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
                └── demo % Main.java: Context context = tomcat.addWebapp("/demo", docBase);
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
