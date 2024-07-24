package com.bronya.bootdemo.controller;

import com.bronya.bootdemo.pojo.Address;
import com.bronya.bootdemo.pojo.Emp;
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

    @RequestMapping("/emp")
    public String emp(HttpServletRequest req) {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        // write to response body
        return name + " " + age; // tom 22
    }

    // ***** Wrapped by Result *****
    @RequestMapping("/emp/res")
    public Result<String> empRes(HttpServletRequest req) {
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        // convert to json and write to response body
        return Result.success(name + " " + age);
    }

    // x-www-form-urlencoded (name=tom, age=22)
    @RequestMapping("/str")
    public String str(@RequestParam(name = "name") String uName,
                      @RequestParam(name = "age", required = false/* default true */) int uAge) {
        // write to response body
        return uName + " " + uAge; // tom 22
    }

    @RequestMapping("/orm") // object relational mapping
    public String orm(Emp p) {
        // write to response body
        return p.toString(); // Emp(name=tom, age=22, address=null)
    }

    @RequestMapping("/obj") // complex object
    public Address obj(Emp p) {
        // convert to json and write to response body
        return p.getAddress();
    }

    // ***** Wrapped by Result *****
    @RequestMapping("/obj/res") // complex object
    public Result<Address> objRes(Emp p) {
        // convert to json and write to response body
        return Result.success(p.getAddress());
    }

    @RequestMapping("/box")
    public String box(String[] like) {
        // write to response body
        return Arrays.toString(like); // [sing, dance, rap, basketball]
    }

    @RequestMapping("/box/list")
    public List<Address> boxList(@RequestParam(name = "city") List<String> cityList) {
        var addrList = new ArrayList<Address>();
        for (String city : cityList) {
            Address addr = new Address("jiangsu", city);
            addrList.add(addr);
        }
        // convert to json and write to response body
        return addrList;
    }

    // ***** Wrapped by Result *****
    @RequestMapping("/box/list/res")
    public Result<List<Address>> boxListRes(@RequestParam(name = "city") List<String> cityList) {
        var addrList = new ArrayList<Address>();
        for (String city : cityList) {
            Address addr = new Address("jiangsu", city);
            addrList.add(addr);
        }
        // convert to json and write to response body
        return Result.success(addrList);
    }

    @RequestMapping("/date")
    public String date(@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        // write to response body
        return date.toString(); // 2002-02-28
    }

    @RequestMapping("/path/{name}/{age}")
    public String path(@PathVariable String name, @PathVariable int age) {
        // write to response body
        return name + " " + age; // tom 22
    }

    /* {
        "name": "tom",
        "age": 22,
        "address": { "province": "jiangsu", "city": "nanjing"}
    } */
    @RequestMapping("/json")
    public String json(@RequestBody Emp p) {
        // write to response body
        return p.toString(); // Emp(name=tom, age=22, address=Address(province=jiangsu, city=nanjing))
    }
}
