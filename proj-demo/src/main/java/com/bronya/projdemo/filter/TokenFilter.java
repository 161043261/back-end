package com.bronya.projdemo.filter;

import com.bronya.projdemo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Deprecated
@Slf4j
@WebFilter(urlPatterns = "/*") // uncomment com.bronya.projdemo.ProjDemoApplication:6 to enable this WebFilter
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        log.info("filter => uri: {}", uri);
        if (uri.equals("/user/login") || uri.equals("/user/register")) {
            chain.doFilter(request, response);
            return;
        }
        String token = req.getHeader("Authorization");
        log.info("filter => token: {}", token);
        if (!StringUtils.hasLength(token)) {
            JwtUtil.noTokenHandler(resp);
            return;
        }
        try {
            Claims claims = JwtUtil.parseJwtString(token);
            for (var key : claims.keySet()) {
                log.info("filter => {}: {}", key, claims.get(key));
            }
        } catch (Exception e) {
            log.info("error message: {}", e.getMessage());
            JwtUtil.noTokenHandler(resp);
            return;
        }
        chain.doFilter(req, resp);
    }
}
