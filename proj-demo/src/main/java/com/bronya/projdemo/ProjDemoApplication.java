package com.bronya.projdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @ServletComponentScan // scan WebFilter, etc.
@SpringBootApplication
public class ProjDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjDemoApplication.class, args);
    }

}