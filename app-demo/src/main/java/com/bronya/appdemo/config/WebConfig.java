package com.bronya.appdemo.config;

import com.bronya.appdemo.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { // Model-View-Controller

    private LoginInterceptor loginInterceptor;

    @Autowired // inject by setter
    public void setLoginInterceptor(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    // /*  1-level path
    // /** n-level path
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
        // .excludePathPatterns("/login");
    }
}
