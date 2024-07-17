package com.bronya.servletDemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.bronya.servletDemo.util.Colors.GREEN;
import static com.bronya.servletDemo.util.Colors.RESET;

@WebFilter(filterName = "filterDemo", initParams = {@WebInitParam(name = "pattern", value = "HH:mm:ss")},
        // urlPatterns is equivalent to value
        urlPatterns = {"*.html",// filter HTML resources
                "/*"}, // filter http://127.0.0.1:8080/demo/*
        servletNames = "helloServlet") // filter the specified servlet
public class FilterDemo implements Filter {
    private static SimpleDateFormat dateFormat;


    public void init(FilterConfig filterConfig) {
        String pattern = filterConfig.getInitParameter(("pattern"));
        dateFormat = new SimpleDateFormat(pattern);
    }

    // req, resp => filterChain => resource
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var req = (HttpServletRequest) servletRequest;
        var resp = (HttpServletResponse) servletResponse;
        String requestURI = req.getRequestURI();

        // Before doFilter() invoked
        long beginTimeMillis = System.currentTimeMillis();
        System.out.println(GREEN + "FilterDemo Filtering: URI=" + requestURI + " @ " + dateFormat.format(new Date()) + RESET);

        // Invoke doFilter() to pass req and resp to the next filter
        filterChain.doFilter(req, resp);

        // After doFilter() invoked
        long endTimeMillis = System.currentTimeMillis();
        System.out.println(GREEN + "Demo Filtered: Total: " + (endTimeMillis - beginTimeMillis) + "ms" + RESET);
    }
}
