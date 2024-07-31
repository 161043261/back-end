package com.bronya.appdemo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bronya.appdemo.annotation.JoinPointAnnotation;
import com.bronya.appdemo.pojo.Emp;
import com.bronya.appdemo.pojo.Result;
import com.bronya.appdemo.service.EmpService;
import com.bronya.appdemo.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {
    private EmpService empService;

    @Autowired // inject by constructor
    public LoginController(EmpService empService) {
        this.empService = empService;
    }

    @JoinPointAnnotation
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("emp={}", emp);
        Emp e = empService.selectEmpByUp(emp);
        if (e != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("name", e.getName());
            claims.put("username", e.getUsername());
            String jwtString = JwtUtils.genJwtString(claims);
            log.warn("jwtString={}", jwtString);
            return Result.success(jwtString);
        }
        return Result.error("username or password incorrect");
    }
}
