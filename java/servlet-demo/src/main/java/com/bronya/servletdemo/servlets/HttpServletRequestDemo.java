package com.bronya.servletdemo.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

/**
 * req.getMethod();
 * req.getRequestURI();
 * req.getRequestURL();
 * req.getScheme();
 * req.getProtocol();
 * req.getLocalPort();
 * req.getServerPort();
 * req.getRemotePort();
 * req.getHeader();
 * req.getHeaderNames();
 * req.getParameter();
 * req.getParameter();
 * req.getParameterNames();
 * req.getParameterMap();
 * req.getReader();
 * req.getInputStream();
 * req.getServletPath()
 */
@WebServlet("/request")
public class HttpServletRequestDemo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        // Request Line
        System.out.println("********** Request Line ********** ");
        System.out.println(req.getMethod());     // GET/POST
        System.out.println(req.getRequestURI()); // /req
        System.out.println(req.getRequestURL()); // http://127.0.0.1:8080/demo/request
        System.out.println(req.getScheme());     // http
        System.out.println(req.getProtocol());   // HTTP/1.1
        // Remote Browser => Proxy Server => Local Tomcat
        System.out.println(req.getLocalPort());  // Local Tomcat Port | 8080
        System.out.println(req.getServerPort()); // Proxy Server Port | 8080
        System.out.println(req.getRemotePort()); // Remote Browser Port

        // Request Headers
        System.out.println("********** Request Headers **********"); // Name: Value
        System.out.println("accept: " + req.getHeader("accept"));
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = req.getHeader(name);
            System.out.println(name + ": " + value);
        }

        // Request K-V Parameters (Request Line & Request Body)
        System.out.println("********** Request K-V Parameters (Request Line & Request Body)  **********");
        String username = req.getParameter("username");
        System.out.println("username: " + username);
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String[] values = req.getParameterValues(name);
            System.out.println(name + ": " + Arrays.toString(values));
        }
        Map<String, String[]> map = req.getParameterMap();
        map.forEach((k, v) -> System.out.println(k + ": " + Arrays.toString(v)));

        // BufferedReader reader = req.getReader(); // character input stream
        // ServletInputStream stream = req.getInputStream(); // byte input stream
        System.out.println(req.getServletPath()); // /req
    }
}
