package com.bronya.servletDemo.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/attribute")
public class ServletAttribute extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext context = getServletContext();
        context.setAttribute("username", "root"); // insert
        context.setAttribute("password", "0000"); // insert
        context.setAttribute("password", "1024"); // update
        String username = (String) context.getAttribute("username"); // select
        System.out.println(username);
        context.removeAttribute("password");
    }
}
