package com.bronya.projdemo.controller;

import com.bronya.projdemo.pojo.Result;
import com.bronya.projdemo.pojo.User;
import com.bronya.projdemo.service.UserService;
import com.bronya.projdemo.utils.JwtUtil;
import com.bronya.projdemo.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
            // 1 httpServletRequest corresponds to 1 thread
            claims.put("username", existingUser.getUsername());
            String token = JwtUtil.genJwtString(claims);
            log.warn("token: {}", token);
            return Result.success(token);
        }
        return Result.error("password is incorrect");
    }


    @GetMapping("/userinfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
        log.info("JWT => username: {}", JwtUtil.parseJwtString(token).get("username", String.class));
        Object claims = ThreadLocalUtil.get();
        assert claims instanceof Map<?, ?>; // assert
        Map<?, ?> claimsMap = (Map<?, ?>) claims;
        log.info("ThreadLocal => username: {}", claimsMap.get("username"));
        User user = userService.selectUserByUsername((String) claimsMap.get("username"));
        return Result.success(user);
    }
}
