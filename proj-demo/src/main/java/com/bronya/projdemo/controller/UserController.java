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
    public Result<String> register(@RequestParam("username") @Valid @Pattern(regexp = "^\\w{4,16}$") String username, @RequestParam("password") @Valid @Pattern(regexp = "^\\w{4,16}$") String password) {
        User existingUser = userService.selectUserByUsername(username);
        if (existingUser != null) {
            return Result.error("username is already taken");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int rowCount = userService.insertUser(user);
        return Result.success("rowCount: " + rowCount);
    }

    @PostMapping("/login")
    public Result<String> login(@RequestParam("username") @Valid @Pattern(regexp = "^\\w{4,16}$") String username, @RequestParam("password") @Valid @Pattern(regexp = "^\\w{4,16}$") String password) {
        User existingUser = userService.selectUserByUsername(username);
        if (existingUser == null) {
            return Result.error("username or password is incorrect");
        }
        String encryption = DigestUtils.md5DigestAsHex(password.getBytes());
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
        User user = userService.selectUserById((int) claimsMap.get("id"));
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result<String> update(@RequestBody @Valid User user) {
        int rowCount = userService.updateUser(user);
        return Result.success("rowCount: " + rowCount);
    }

    @PatchMapping("/updateAvatar")
    public Result<String> updateAvatar(@RequestParam @URL String avatarUrl) {
        int rowCount = userService.updateAvatar(avatarUrl);
        return Result.success("rowCount: " + rowCount);
    }

    // todo http://127.0.0.1:8080/user/updatePwd { "pwd": ?, "new_pwd": ?, "confirm_pwd": ? }
    @Valid
    @PatchMapping("/updatePwd")
    public Result<String> updatePwd(@RequestBody Map<String, String> paramsMap) {
        String pwd = paramsMap.get("pwd");
        String newPwd = paramsMap.get("new_pwd");
        String confirmPwd = paramsMap.get("confirm_pwd");
        if (!java.util.regex.Pattern.matches("^\\w{4,16}$", newPwd) || newPwd.equals(pwd) || !newPwd.equals(confirmPwd)) {
            return Result.error("invalid update");
        }
        Object claims = ThreadLocalUtil.get();
        assert claims instanceof Map<?, ?>;
        Map<?, ?> claimsMap = (Map<?, ?>) claims;
        int id = (int) claimsMap.get("id");
        User existingUser = userService.selectUserById(id);
        String encryption = DigestUtils.md5DigestAsHex(pwd.getBytes());
        if (!encryption.equals(existingUser.getPassword())) {
            return Result.error("password error");
        }
        int rowCount = userService.updatePwd(existingUser.getId(), newPwd);
        return Result.success("rowCount: " + rowCount);
    }
}
