package com.bronya.bootdemo.controller;

import com.bronya.bootdemo.pojo.People;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PeopleController {

    @RequestMapping("/people")
    public String people(HttpServletRequest req) {
        // get request line parameters
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        return name + " " + age; // tom 22
    }

    // x-www-form-urlencoded
    // name = tom; age = 22;
    @RequestMapping("/simpeople") // simple people
    public String simPeople(@RequestParam(name = "name") String uName, @RequestParam(name = "age", required = false/* default true */) int uAge) {
        return uName + " " + uAge; // tom 22
    }

    @RequestMapping("/ormpeople") // object relational mapping
    public String ormPeople(People p) {
        return p.toString(); // People(name=tom, age=22, address=null)
    }

    @RequestMapping("/compeople") // complex people
    public String comPeople(People p) {
        return p.toString(); // People(name=tom, age=22, address=Address(province=jiangsu, city=nanjing))
    }
}
