package com.bronya.mvnTomcat.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/context")
public class ServletContextTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext[] contexts = new ServletContext[3];
        // 1. get ServletContext through this or super
        contexts[0] = this.getServletContext();
        // 2. get ServletContext through ServletConfig
        ServletConfig config = getServletConfig();
        contexts[1] = config.getServletContext();
        // 3. get ServletContext through request
        contexts[2] = req.getServletContext();
        System.out.println(contexts[0] == contexts[1] && contexts[0] == contexts[2]); // true
        Enumeration<String> names = contexts[0].getInitParameterNames();
        StringBuilder builder = new StringBuilder();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = contexts[0].getInitParameter(name);
            builder.append(name).append(": ").append(value).append("\n");
        }
        PrintWriter writer = resp.getWriter();
        writer.write(builder.toString());
    }
}
