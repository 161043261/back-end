package com.bronya.servletDemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter(filterName = "FilterB", urlPatterns = "/hello")
public class FilterB implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterB Filtering...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("FilterB Filtered...");
    }
}
