package com.bronya.projdemo.interceptor;

import com.bronya.projdemo.utils.JwtUtil;
import com.bronya.projdemo.utils.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;


@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("interceptor => uri: {}", uri);
        String token = request.getHeader("Authorization");
        log.info("interceptor => token: {}", token);
        if (!StringUtils.hasLength(token)) {
            JwtUtil.noTokenHandler(response);
            return false;
        }
        try {
            Claims claims = JwtUtil.parseJwtString(token);
            for (var key : claims.keySet()) {
                log.info("interceptor => {}: {}", key, claims.get(key));
            }
            ThreadLocalUtil.set(claims);
        } catch (Exception e) {
            log.info("error message: {}", e.getMessage());
            JwtUtil.noTokenHandler(response);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtil.remove(); // preventing memory leaks
    }
}
