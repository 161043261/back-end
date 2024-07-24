package com.bronya.servletdemo.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/forward")
public class RequestForward extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ***** 1. You can forward to another servlet *****
        System.out.println("Forwarding to http://127.0.0.1/demo/hello?username=forward&password=1024");
        // equivalent to RequestDispatcher dispatcher = req.getRequestDispatcher("./hello");
        RequestDispatcher dispatcher = req.getRequestDispatcher("hello");
        dispatcher.forward(req, resp);

        // ***** 2. You can also forward to internal web resource (docBase = "./src/main/webapp") *****
        // System.out.println("Forwarding to ./static/forwarded.html");
        // RequestDispatcher dispatcher = req.getRequestDispatcher("./static/forwarded.html");
        // dispatcher.forward(req, resp);

        // ***** 3. You can NOT forward to external web resource (servlet cannot find it) *****
    }
}
