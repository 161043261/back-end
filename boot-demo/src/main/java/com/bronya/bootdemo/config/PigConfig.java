package com.bronya.bootdemo.config;

import com.bronya.bootdemo.bean.Kun;
import com.bronya.bootdemo.bean.Pig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties(value = Kun.class)
// 1. Enable properties configuration for class Kun
// 2. Register a kun bean(component) to the IOC container

// @EnableConfigurationProperties is commonly used to enable properties configuration for third-party class
@SpringBootConfiguration
public class PigConfig {

    @Bean(name = "pigBean")
    @ConfigurationProperties(prefix = "pig")
    public Pig setPigBean() {
        return new Pig();
    }
}
