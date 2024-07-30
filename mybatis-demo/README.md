# MyBatis

### jdbc.properties template

```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/dbname
jdbc.username=root
jdbc.password=root
```

### mybatis-config.xml template

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "https://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="./jdbc.properties"/>
    <settings>
        <!-- e.g. map "user_attribute" to "userAttribute" -->
        <setting name="mapUnderscoreToCamelCase" value="true"/> <!-- default false -->
    </settings>
    <typeAliases>
        <!-- alias "com.apple.project.pojo.Demo" to "Demo" -->
        <package name="com.apple.project.pojo"/>
    </typeAliases>
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <!-- com.mysql.cj.jdbc.Driver -->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <!-- jdbc:mysql:///dbname -->
                <property name="url" value="jdbc:mysql://localhost:3306/dbname"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <mappers> <!-- pwd = ${project}/src/main/resources -->
        <!-- <mapper resource="./com/apple/project/mapper/DemoMapper.xml"/> -->

        <package name="./com/apple/project/mapper"/>
        <!-- equivalent to
             <mapper resource="./com/apple/project/mapper/*.xml"/> -->
    </mappers>
</configuration>
```

### DemoMapper.xml template

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- DemoMapper.class.getName() = "com.apple.project.mapper.DemoMapper" -->
<mapper namespace="com.apple.project.mapper.DemoMapper">
    <!-- insert, delete, update, select -->
</mapper>
```
