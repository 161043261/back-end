package com.bronya.bootdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller
// @ResponseBody
@RestController  // @RestController = @ResponseBody + @Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
