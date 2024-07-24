package com.bronya.servletdemo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookie")
public class CookieDemo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        // ***** Get Cookies from Request Headers *****
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                writer.write(cookie.getName() + ": " + cookie.getValue() + "<br>");
            }
        } else { // cookies == null
            // ***** Set Cookies *****
            Cookie username = new Cookie("username", "cookie");
            Cookie password = new Cookie("password", "1024");
            password.setMaxAge(60 /* seconds */);  // persistent storage (TTL = 60s)
            // The 'password' cookie will be carried
            // ONLY when requesting http://127.0.0.1:8080/demo/hello
            password.setPath("/demo/hello");

            // ***** Add Cookies to Response Headers *****
            resp.addCookie(username); // resp.setHeader("Set-Cookie", "username=cookie");
            resp.addCookie(password); // resp.setHeader("Set-Cookie", "password=1024");
        }
    }
}
