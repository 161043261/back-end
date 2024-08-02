package com.bronya.projdemo.controller;

import com.bronya.projdemo.pojo.Result;
import com.bronya.projdemo.pojo.User;
import com.bronya.projdemo.service.UserService;
import com.bronya.projdemo.utils.JwtUtil;
import com.bronya.projdemo.utils.ThreadLocalUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
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
    public Result<String> register(@RequestParam("username") @Valid @Pattern(regexp = "^\\S{4,16}$") String username, @RequestParam("password") @Valid @Pattern(regexp = "^\\S{4,16}$") String password) {
        User existingUser = userService.selectUserByUsername(username);
        if (existingUser != null) {
            return Result.error("Username Already Exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int rowCount = userService.insertUser(user);
        return Result.success("Register OK", "rowCount=" + rowCount);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam("username") @Valid @Pattern(regexp = "^\\S{4,16}$") String username, @RequestParam("password") @Valid @Pattern(regexp = "^\\S{4,16}$") String password) {
        User existingUser = userService.selectUserByUsername(username);
        if (existingUser == null) {
            return Result.error("Username or Password ERROR");
        }
        String encryption = DigestUtils.md5DigestAsHex(password.getBytes());
        if (encryption.equals(existingUser.getPassword())) {
            var claims = new HashMap<String, Object>();
            claims.put("id", existingUser.getId());
            // 1 httpServletRequest corresponds to 1 thread
            claims.put("username", existingUser.getUsername());
            String token = JwtUtil.genJwtString(claims);
            log.warn("token: {}", token);
            return Result.success("Login OK", token);
        }
        return Result.error("Password ERROR");
    }

    @GetMapping("/userinfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
        log.info("JWT => username: {}", JwtUtil.parseJwtString(token).get("username", String.class));
        Map<String, Object> claims = ThreadLocalUtil.get();
        log.info("ThreadLocal => username: {}", claims.get("username"));
        User user = userService.selectUserById((int) claims.get("id"));
        return Result.success("Get User Profile OK", user);
    }

    @PutMapping("/update")
    public Result<String> updateUser(@RequestBody @Valid User user) {
        int rowCount = userService.updateUser(user);
        return Result.success("Update User Profile OK", "rowCount=" + rowCount);
    }

    @PatchMapping("/updateAvatar")
    public Result<String> updateAvatar(@RequestParam @URL String avatarUrl) {
        int rowCount = userService.updateAvatar(avatarUrl);
        return Result.success("Update User Avatar OK", "rowCount=" + rowCount);
    }

    // todo http://127.0.0.1:8080/user/updatePwd { "pwd": ?, "new_pwd": ?, "confirm_pwd": ? }
    @Valid
    @PatchMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody Map<String, String> paramsMap) {
        String pwd = paramsMap.get("pwd");
        String newPwd = paramsMap.get("new_pwd");
        String confirmPwd = paramsMap.get("confirm_pwd");
        if (!java.util.regex.Pattern.matches("^\\S{4,16}$", newPwd) || newPwd.equals(pwd) || !newPwd.equals(confirmPwd)) {
            return Result.error("Update ERROR");
        }
        Map<String, Object> claims = ThreadLocalUtil.get();
        int id = (int) claims.get("id");
        User existingUser = userService.selectUserById(id);
        String encryption = DigestUtils.md5DigestAsHex(pwd.getBytes());
        if (!encryption.equals(existingUser.getPassword())) {
            return Result.error("Password ERROR");
        }
        int rowCount = userService.updatePwd(existingUser.getId(), newPwd);
        return Result.success("Update Password OK", "rowCount=" + rowCount);
    }
}
