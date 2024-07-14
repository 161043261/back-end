package com.cognosphere.demo.controller;

// import org.springframework.stereotype.Controller
// import org.springframework.web.bind.annotation.ResponseBody;

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
