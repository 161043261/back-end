package com.bronya.servletdemo.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

// value is equivalent to urlPatterns
@WebServlet(name = "helloServlet", value = "/hello") // urlPatterns = {"/hello"}
public class HelloServlet extends HttpServlet {

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html"); // resp.setHeader("Content-Type", "text/html");
        resp.setStatus(HttpServletResponse.SC_OK); // StatusCode_OK
        PrintWriter writer = resp.getWriter();
        writer.write("<h1>Hello World</h1>");

        // get parameters from Request Line and Request Body
        req.getParameterMap().forEach((name, value) -> writer.write(name + ": " + Arrays.toString(value) + "<br>"));
        // get cookies from Request Headers
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies).forEach(cookie -> {
                writer.write(cookie.getName() + ": " + cookie.getValue() + "<br>");
            });
        }
        writer.write("<h3>Get servletContext attributes</h3>");
        ServletContext context = this.getServletContext();
        String serverName = (String) context.getAttribute("serverName");
        if (serverName != null) {
            writer.write("serverName: " + serverName);
        }
        // can be omitted
        writer.flush();
        writer.close();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        handle(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        handle(req, resp);
    }
}
