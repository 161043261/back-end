package com.cognosphere.demo.config;


import com.cognosphere.demo.bean.Usr;
import com.mysql.cj.jdbc.Driver; // "com.mysql.jdbc.Driver" is deprecated
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

// @Configuration
@SpringBootConfiguration // @SpringBootConfiguration ≈ @Configuration

// import third-party class and register a com.mysql.cj.jdbc.Driver bean
// the default beanName equals to the full className com.mysql.cj.jdbc.Driver
@Import(Driver.class)

public class UsrConfig {

    @Bean(name = "usrBean")
    @Scope(scopeName = "singleton") // singleton (default), prototype

    // the default beanName equals to the methodName setUsrBean
    public Usr setUsrBean() {
        Usr usr = new Usr();
        usr.setName("admin");
        return usr;
    }
}
