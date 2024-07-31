package com.bronya.projdemo.controller;

import com.bronya.projdemo.pojo.Result;
import com.bronya.projdemo.pojo.User;
import com.bronya.projdemo.service.UserService;
import com.bronya.projdemo.utils.JwtUtil;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Validated
@Slf4j
@RestController // @Controller + @ResponseBody
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        check(user.getUsername(), user.getPassword());
        User existingUser = userService.selectUserByUsername(user.getUsername());
        if (existingUser != null) {
            return Result.error("username is already taken");
        }
        int rowCount = userService.insertUser(user);
        return Result.success("rowCount: " + rowCount);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user) {
        User existingUser = userService.selectUserByUsername(user.getUsername());
        if (existingUser == null) {
            return Result.error("username or password is incorrect");
        }
        String encryption = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        if (encryption.equals(existingUser.getPassword())) {
            var claims = new HashMap<String, Object>();
            claims.put("id", existingUser.getId());
            claims.put("username", existingUser.getUsername());
            String token = JwtUtil.genJwtString(claims);
            log.warn("token: {}", token);
            return Result.success(token);
        }
        return Result.error("password is incorrect");
    }

    // fixme invalid
    private void check(@Pattern(regexp = "[A-Za-z0-9]{4,16}") String username,
                       @Pattern(regexp = "[A-Za-z0-9]{4,16}") String password) {
        log.info("username: {}, password: {}", username, password);
    }
}
