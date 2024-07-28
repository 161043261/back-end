package com.bronya.manage.interceptor;

import com.bronya.manage.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import static com.bronya.manage.utils.Colors.GREEN;
import static com.bronya.manage.utils.Colors.RESET;
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
        System.out.println(GREEN + "preHandle" + RESET);
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

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println(GREEN + "postHandle" + RESET);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println(GREEN + "afterCompletion" + RESET);
    }
}
