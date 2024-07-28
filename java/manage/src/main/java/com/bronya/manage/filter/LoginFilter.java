package com.bronya.manage.filter;

import com.bronya.manage.pojo.Result;
import com.bronya.manage.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = (HttpServletRequest) request;
        var resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.equals("/login")) {
            chain.doFilter(req, resp);
            return;
        }

        String jwsString = req.getHeader("token");
        if (jwsString == null || jwsString.isEmpty()) {
            error(resp);
            return;
        }

        try {
            Claims claims = JwtUtils.parseJwsString(jwsString);
            for (var key : claims.keySet()) {
                log.info("{}: {}", key, claims.get(key));
            }
        } catch (Exception e) {
            log.info("{}", e.getMessage());
            error(resp);
            return;
        }

        chain.doFilter(req, resp);
    }

    private void error(@NotNull HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String jsonString = ow.writeValueAsString(Result.error("NOT_LOGIN"));
        System.out.println(jsonString);
        PrintWriter writer = resp.getWriter();
        writer.write(jsonString);
    }
}
