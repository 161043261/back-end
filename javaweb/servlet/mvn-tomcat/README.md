# mvn-tomcat
The key point of Learning Java is to deprecate XML, except Maven.

### requirement
Tomcat 9.x (Tomcat 10.x is NOT supported)

### test
[HelloServlet](http://127.0.0.1:8080/hello?username=root&password=1024&encoding=utf-8)

[ServletConfigTest](http://127.0.0.1:8080/config)

[ServletContextTest](http://127.0.0.1:8080/context)

[ServletLifeCycle](http://127.0.0.1:8080/lifecycle)

[UploadServlet](http://127.0.0.1:8080/upload?username=root&password=1024&encoding=utf-8)

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
│           ├── WEB-INF
│           │   └── web.xml
│           └── upload % servletContext.getRealPath("./upload");
│               └── parameters.txt
└── target
    ├── classes
    │   ├── Main.class % Main.java => Main.class
    │   └── com
    │       └── bronya
    │           └── mvnTomcat
    │               └── servlet
    │                   └── *.class % *.java => *.class
    ├── mvn-tomcat
    │   ├── META-INF
    │   ├── WEB-INF
    │   │   ├── classes
    │   │   │   ├── Main.class
    │   │   │   └── com
    │   │   │       └── bronya
    │   │   │           └── mvnTomcat
    │   │   │               └── servlet
    │   │   │                   └── *.class
    │   │   ├── lib
    │   │   │   └── *.jar
    │   │   └── web.xml
    │   └── upload
    │       └── parameters.txt
    └── mvn-tomcat.war
```