package com.bronya.projdemo.controller;

import com.bronya.projdemo.dao.Result;
import com.bronya.projdemo.dao.User;
import com.bronya.projdemo.service.UserService;
import com.bronya.projdemo.utils.JwtUtil;
import com.bronya.projdemo.utils.ThreadLocalUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
    public Result<String> register(@RequestParam("username") @Valid @Pattern(regexp = "^\\S{4,16}$") String username,
                                   @RequestParam("password") @Valid @Pattern(regexp = "^\\S{4,16}$") String password) {
        User existingUser = userService.selectUserByUsername(username);
        if (existingUser != null) {
            return Result.err("Username Exists");
        }
        User user = new User();
        user.setUsername(username);
        user.setName(username);
        user.setPassword(password);
        int rowCount = userService.insertUser(user);
        return Result.ok("Register OK", "rowCount=" + rowCount);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam("username") @Valid @Pattern(regexp = "^\\S{4,16}$") String username,
                                @RequestParam("password") @Valid @Pattern(regexp = "^\\S{4,16}$") String password) {
        User existingUser = userService.selectUserByUsername(username);
        if (existingUser == null) {
            return Result.err("Username or Password Error");
        }
        String encryption = DigestUtils.md5DigestAsHex(password.getBytes());
        if (encryption.equals(existingUser.getPassword())) {
            var claims = new HashMap<String, Object>();
            claims.put("id", existingUser.getId());
            // 1 httpServletRequest corresponds to 1 thread
            claims.put("username", existingUser.getUsername());
            String token = JwtUtil.genJwtString(claims);
            log.warn("token: {}", token);
            return Result.ok("Login OK", token);
        }
        return Result.err("Password Error");
    }

    @GetMapping("/profile")
    public Result<User> profile(@RequestHeader(name = "Authorization") String token) {
        log.info("JWT => username: {}", JwtUtil.parseJwtString(token).get("username", String.class));
        Map<String, Object> claims = ThreadLocalUtil.get();
        log.info("ThreadLocal => username: {}", claims.get("username"));
        User user = userService.selectUserById((Integer) claims.get("id"));
        return Result.ok("Get User Profile OK", user);
    }

    @PutMapping("/update")
    public Result<String> updateUser(@RequestBody @Valid User user) {
        int rowCount = userService.updateUser(user);
        return Result.ok("Update User Profile OK", "rowCount=" + rowCount);
    }

    @PatchMapping("/avatar")
    public Result<String> updateAvatar(@RequestParam String avatar) {
        int rowCount = userService.updateAvatar(avatar);
        return Result.ok("Update User Avatar OK", "rowCount=" + rowCount);
    }

    // todo http://127.0.0.1:8080/user/pwd { "pwd": ?, "new_pwd": ?, "confirm_pwd": ? }
    @Valid
    @PatchMapping("/pwd")
    public Result<String> updatePwd(@RequestBody Map<String, String> paramsMap) {
        String pwd = paramsMap.get("pwd");
        String newPwd = paramsMap.get("newPwd");
        String confirmPwd = paramsMap.get("confirmPwd");
        if (!java.util.regex.Pattern.matches("^\\S{4,16}$", newPwd) || newPwd.equals(pwd) || !newPwd.equals(confirmPwd)) {
            return Result.err("Invalid Update");
        }
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        User existingUser = userService.selectUserById(id);
        String encryption = DigestUtils.md5DigestAsHex(pwd.getBytes());
        if (!encryption.equals(existingUser.getPassword())) {
            return Result.err("Update Password Error");
        }
        int rowCount = userService.updatePwd(existingUser.getId(), newPwd);
        return Result.ok("Update Password OK", "rowCount=" + rowCount);
    }
}
