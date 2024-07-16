package com.bronya.mvnTomcat.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redirect")
public class Redirect extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ***** 1. You can redirect to another servlet *****
        System.out.println("Redirect to http://127.0.0.1/servlet/hello");
        resp.setStatus(HttpServletResponse.SC_FOUND); // 302, this can be omitted
        resp.sendRedirect("hello");

        // // ***** 2. You can NOT redirect to internal web resource *****
        // System.out.println("Redirect to ./index.html");
        // resp.sendRedirect("./index.html");

        // // ***** 3. You can also redirect to external web resource *****
        // System.out.println("Redirect to https://ys.mihoyo.com");
        // resp.sendRedirect("https://ys.mihoyo.com");
    }
}
