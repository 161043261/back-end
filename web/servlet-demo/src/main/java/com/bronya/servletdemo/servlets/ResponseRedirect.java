package com.bronya.servletdemo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirect")
public class ResponseRedirect extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ***** 1. You can redirect to another servlet *****
        System.out.println("Redirecting to http://127.0.0.1/demo/hello");
        resp.setStatus(HttpServletResponse.SC_FOUND); // 302, this can be omitted
        resp.sendRedirect("./hello"); // equivalent to resp.sendRedirect("/demo/hello");

        // ***** 2. You can NOT redirect to internal web resource (client cannot find it) *****

        // // ***** 3. You can also redirect to external web resource *****
        // System.out.println("Redirecting to https://ys.mihoyo.com");
        // resp.sendRedirect("https://ys.mihoyo.com");
    }
}
