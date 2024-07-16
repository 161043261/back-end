package com.bronya.servletDemo.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/context")
public class ServletContextTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext[] context = new ServletContext[3];
        // 1. get ServletContext through this or super
        context[0] = this.getServletContext();
        // 2. get ServletContext through ServletConfig
        ServletConfig config = getServletConfig();
        context[1] = config.getServletContext();
        // 3. get ServletContext through request
        context[2] = req.getServletContext();
        System.out.println(context[0] == context[1] && context[0] == context[2]); // true
        resp.setContentType("text/html");
        StringBuilder builder = new StringBuilder("<h1>initParameters</h1>");
        // initParameter
        Enumeration<String> names = context[0].getInitParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = context[0].getInitParameter(name);
            builder.append(name).append(": ").append(value).append("<br>");
        }
        // attribute
        builder.append("<h1>attributes</h1>");
        String username = (String) context[0].getAttribute("username");
        builder.append("username: ").append(username).append("<br>");
        PrintWriter writer = resp.getWriter();
        writer.write(builder.toString());
    }
}
