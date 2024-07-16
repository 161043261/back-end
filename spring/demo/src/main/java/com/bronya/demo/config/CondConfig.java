package com.bronya.demo.config;

import com.bronya.demo.bean.Cat;
import com.bronya.demo.bean.Dog;
import com.bronya.demo.bean.User;
import com.mysql.cj.jdbc.Driver;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class CondConfig {

    @Bean("catBean")
    @ConditionalOnClass(value = Driver.class)
    public Cat setCatBean() {
        return new Cat();
    }

    @Bean("dogBean")
    @ConditionalOnMissingClass(value = "com.mysql.cj.jdbc.Driver")
    public Dog setDogBean() {
        return new Dog();
    }

    @Bean("tomcatBean")
    @ConditionalOnBean(value = Dog.class)
    public User setTomcatBean() {
        var tomcat = new User();
        tomcat.setName("tomcat");
        return tomcat;
    }

    @Bean("nginxBean")
    @ConditionalOnMissingBean(value = Dog.class)
    public User setNginxBean() {
        var nginx = new User();
        nginx.setName("nginx");
        return nginx;
    }
}
