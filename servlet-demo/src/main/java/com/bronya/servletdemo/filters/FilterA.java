package com.bronya.servletdemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * Filter Life Cycle
 * Constructing <- constructor()
 * Initializing <- init()
 * Filtering <- doFilter()
 * Destroying <- destroy()
 */
@WebFilter(filterName = "FilterA", urlPatterns = "/hello")
public class FilterA implements Filter {
    public FilterA() {
        System.out.println("Constructing Filter..."); // Invoked when the Tomcat starts
    }

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("Initializing Filter...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("A Filtering...");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("A Filtered...");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Filter...");
    }
}
