package com.bronya.servletdemo.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/config", initParams = {@WebInitParam(name = "username", value = "root"), @WebInitParam(name = "password", value = "1024")})
public class ServletConfigDemo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletConfig config = super.getServletConfig();
        Enumeration<String> names = config.getInitParameterNames();
        PrintWriter writer = resp.getWriter();
        if (!names.hasMoreElements()) {
            writer.write("No parameters found");
            return;
        }
        resp.setContentType("text/html");
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = config.getInitParameter(name);
            writer.write(name + ": " + value + "<br>");
        }
    }
}
