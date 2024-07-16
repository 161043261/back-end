package com.bronya.demo;

import com.bronya.demo.bean.*;
import com.mysql.cj.jdbc.Driver;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootConfiguration
// @EnableAutoConfiguration
// @ComponentScan("com.bronya.demo")
@SpringBootApplication(scanBasePackages = "com.bronya.demo")
public class DemoApplication {
    public static void main(String[] args) {
        // IOC, Inversion of Control
        // applicationContext: IOC Container
        var applicationContext = SpringApplication.run(DemoApplication.class, args);

        // You can get bean by name and/or class
        var usrBean0 = applicationContext.getBean("usrBean");
        var usrBean1 = applicationContext.getBean(Usr.class);
        System.out.println(usrBean0 == usrBean1); // true (singleton)
        var driverBean = applicationContext.getBean("com.mysql.cj.jdbc.Driver", Driver.class);
        System.out.println(driverBean);

        var names = applicationContext.getBeanDefinitionNames();
        for (var name : names) {
            System.out.println(name);
        }
        // demoApplication => Instance of ./DemoApplication
        // pig             => Instance of ./bean/Pig                      by @Component
        // xxxConfig       => Instance of ./config/XxxConfig              by @Config | @SpringBootConfig
        // xxxController   => Instance of ./controller/XxxController      by @Controller | @RestController
        // xxxBean         => Defined  in ./config/XxxConfig setXxxBean() by @Bean
        // com.mysql.cj.jdbc.Driver

        System.out.println(applicationContext.getBean(Cat.class));
        try {
            System.out.println(applicationContext.getBean(Dog.class)); // com.bronya.demo.bean.Cat
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("No such bean: " + Dog.class.getName());
        }
        System.out.println(applicationContext.getBean(User.class).getName()); // nginx

        var pigNames = applicationContext.getBeanNamesForType(Pig.class);
        for (var pigName : pigNames) {
            System.out.println(pigName); // pig, pigBean
        }

        System.out.println(applicationContext.getBean(Kun.class)); // Kun{id=2, name='"kun"'}
    }
}
