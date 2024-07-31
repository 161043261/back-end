package com.bronya.appdemo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bronya.appdemo.utils.JwtUtils;
import static com.bronya.appdemo.utils.JwtUtils.noJwtString;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String url = req.getRequestURL().toString();
        if (url.contains("login")) {
            return true;
        }

        String jwtString = req.getHeader("token");
        if (jwtString == null || jwtString.isEmpty()) {
            noJwtString(resp);
            return false;
        }
        try {
            Claims claims = JwtUtils.parseJwtString(jwtString);
            for (var key : claims.keySet()) {
                log.info("interceptor => {}: {}", key, claims.get(key));
            }
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            noJwtString(resp);
            return false;
        }
        return true;
    }
}
