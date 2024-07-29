package com.bronya.manage.interceptor;

import com.bronya.manage.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.bronya.manage.utils.JwtUtils.noJwsString;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        String url = req.getRequestURL().toString();
        if (url.contains("login")) {
            return true;
        }

        String jwsString = req.getHeader("token");
        if (jwsString == null || jwsString.isEmpty()) {
            noJwsString(resp);
            return false;
        }
        try {
            Claims claims = JwtUtils.parseJwsString(jwsString);
            for (var key : claims.keySet()) {
                log.info("interceptor => {}: {}", key, claims.get(key));
            }
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            noJwsString(resp);
            return false;
        }
        return true;
    }
}
