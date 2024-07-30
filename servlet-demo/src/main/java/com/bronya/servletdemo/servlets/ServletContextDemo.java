package com.bronya.servletdemo.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/context")
public class ServletContextDemo extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext[] context = new ServletContext[3];
        // 1. get ServletContext through this or super
        context[0] = this.getServletContext(); // equivalent to `this.getServletConfig().getServletContext()`

        // 2. get ServletContext through ServletConfig
        ServletConfig config = this.getServletConfig();
        context[1] = config.getServletContext();

        // 3. get ServletContext through request
        context[2] = req.getServletContext();
        System.out.println(context[0] == context[1] && context[0] == context[2]); // true
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();
        writer.write("<h1>initParameters</h1>");

        // initParameter
        Enumeration<String> names = context[0].getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = context[0].getInitParameter(name);
            writer.write(name + ": " + value + "<br>");

        }

        // attributes
        writer.write("<h1>attributes</h1>");
        String serverName = (String) context[0].getAttribute("serverName");

        if (serverName != null) {
            writer.write("serverName: " + serverName + "<br>");
            return;
        }

        // Set servletContext attributes (use firefox)
        // ***** insert *****
        context[0].setAttribute("serverName", "Tomcat");
        context[0].setAttribute("serverPort", "8000");

        // ***** update *****
        context[0].setAttribute("serverPort", "8080");

        // ***** delete *****
        context[0].removeAttribute("serverPort");

        // ***** select *****
        serverName = (String) context[0].getAttribute("serverName");
        writer.write("serverName: " + serverName + "<br>");
    }
}
