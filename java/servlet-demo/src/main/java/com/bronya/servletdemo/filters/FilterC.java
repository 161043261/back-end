package com.bronya.servletdemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(filterName = "FilterC", urlPatterns = "/hello")
public class FilterC implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("C Filtering...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("C Filtered...");
    }
}
