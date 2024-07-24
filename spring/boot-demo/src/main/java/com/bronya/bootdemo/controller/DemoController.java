package com.bronya.bootdemo.controller;

import com.bronya.bootdemo.pojo.Address;
import com.bronya.bootdemo.pojo.People;
import com.bronya.bootdemo.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController // @RestController = @Controller + @ResponseBody
public class DemoController {

    @RequestMapping("/people")
    public String people(HttpServletRequest req) {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        return name + " " + age; // tom 22
    }

    // ***** Wrapped by Result *****
    @RequestMapping("/people/result")
    public Result<String> peopleResult(HttpServletRequest req) {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        return Result.success(name + " " + age); // tom 22
    }

    // x-www-form-urlencoded (name=tom, age=22)
    @RequestMapping("/simple") // simple object
    public String simple(@RequestParam(name = "name") String uName, @RequestParam(name = "age", required = false/* default true */) int uAge) {
        return uName + " " + uAge; // tom 22
    }

    @RequestMapping("/orm") // object relational mapping
    public String orm(People p) {
        return p.toString(); // People(name=tom, age=22, address=null)
    }

    @RequestMapping("/complex") // complex object
    public Address complex(People p) {
        return p.getAddress();
    }

    // ***** Wrapped by Result *****
    @RequestMapping("/complex/result") // complex object
    public Result<Address> complexResult(People p) {
        return Result.success(p.getAddress());
    }

    @RequestMapping("/checkbox") // checkbox
    public String checkbox(String[] like) {
        return Arrays.toString(like); // [sing, dance, rap, basketball]
    }

    @RequestMapping("/checkbox/list") // checkbox
    public List<Address> checkboxList(@RequestParam(name = "city") List<String> cityList) {
        var addrList = new ArrayList<Address>();
        for (String city : cityList) {
            Address addr = new Address("jiangsu", city);
            addrList.add(addr);
        }
        return addrList;
    }

    // ***** Wrapped by Result *****
    @RequestMapping("/checkbox/list/result") // checkbox
    public Result<List<Address>> checkboxListResult(@RequestParam(name = "city") List<String> cityList) {
        var addrList = new ArrayList<Address>();
        for (String city : cityList) {
            Address addr = new Address("jiangsu", city);
            addrList.add(addr);
        }
        return Result.success(addrList);
    }

    @RequestMapping("/date") // date
    public String date(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return date.toString(); // 2002-02-28
    }

    @RequestMapping("/path/{name}/{age}")
    public String path(@PathVariable String name, @PathVariable int age) {
        return name + " " + age; // tom 22
    }

    /* {
        "name": "tom",
        "age": 22,
        "address": { "province": "jiangsu", "city": "nanjing"}
    } */ // ResponseBody
    @RequestMapping("/json")
    public String json(@RequestBody People p) {
        return p.toString(); // People(name=tom, age=22, address=Address(province=jiangsu, city=nanjing))
    }
}
