package com.bronya.servletDemo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/cookie")
public class ResponseCookie extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // create cookie
        Cookie username = new Cookie("username", "cookie");
        Cookie password = new Cookie("password", "1024");
        resp.addCookie(username);
        resp.addCookie(password);
    }
}
