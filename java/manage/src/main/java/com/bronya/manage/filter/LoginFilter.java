package com.bronya.manage.filter;

import com.bronya.manage.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import static com.bronya.manage.utils.JwtUtils.noJwsString;

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

        String jwsString = req.getHeader("token");
        if (jwsString == null || jwsString.isEmpty()) {
            noJwsString(resp);
            return;
        }

        try {
            Claims claims = JwtUtils.parseJwsString(jwsString);
            for (var key : claims.keySet()) {
                log.info("filter => {}: {}", key, claims.get(key));
            }
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            noJwsString(resp);
            return;
        }
        chain.doFilter(req, resp);
    }
}
