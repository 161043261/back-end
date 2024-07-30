package com.bronya.appdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class AppDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppDemoApplication.class, args);
    }
}
