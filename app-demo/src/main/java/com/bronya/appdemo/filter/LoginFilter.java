package com.bronya.appdemo.filter;

import java.io.IOException;

import com.bronya.appdemo.utils.JwtUtils;
import static com.bronya.appdemo.utils.JwtUtils.noJwtString;

import io.jsonwebtoken.Claims;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        String url = req.getRequestURL().toString();
        if (url.contains("login")) {
            chain.doFilter(req, resp);
            return;
        }

        String jwtString = req.getHeader("token");
        if (jwtString == null || jwtString.isEmpty()) {
            noJwtString(resp);
            return;
        }

        try {
            Claims claims = JwtUtils.parseJwtString(jwtString);
            for (var key : claims.keySet()) {
                log.info("filter => {}: {}", key, claims.get(key));
            }
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            noJwtString(resp);
            return;
        }
        chain.doFilter(req, resp);
    }
}
