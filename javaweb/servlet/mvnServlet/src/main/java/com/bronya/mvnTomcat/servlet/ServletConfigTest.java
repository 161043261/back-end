package com.bronya.mvnTomcat.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "config",
        urlPatterns = "/config",
        initParams = {
                @WebInitParam(name = "username", value = "root"),
                @WebInitParam(name = "password", value = "1024")
        })
public class ServletConfigTest extends HttpServlet {

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
        StringBuilder builder = new StringBuilder();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            String value = config.getInitParameter(name);
            builder.append(name).append(": ").append(value).append("<br>");
        }
        writer.write(builder.toString());
    }
}
