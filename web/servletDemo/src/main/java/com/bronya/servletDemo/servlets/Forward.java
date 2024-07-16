package com.bronya.servletDemo.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forward")
public class Forward extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // // ***** 1. You can forward to another servlet *****
        // System.out.println("Forwarding to http://127.0.0.1/servlet/hello");
        // RequestDispatcher dispatcher = req.getRequestDispatcher("hello");
        // dispatcher.forward(req, resp);

        // ***** 2. You can also forward to internal web resource (docBase = "./src/main/webapp") *****
        System.out.println("Forwarding to ./static/forward.html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("./static/forward.html");
        dispatcher.forward(req, resp);

        // ***** 3. You can NOT forward to external web resource (servlet cannot find it) *****
    }
}
