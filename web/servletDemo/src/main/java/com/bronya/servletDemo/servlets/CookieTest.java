package com.bronya.servletDemo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/cookie")
public class CookieTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder = new StringBuilder();
        // ***** Get Cookies from Request Headers *****
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                builder.append(cookie.getName()).append(": ").append(cookie.getValue()).append("<br>");
            }
        } else { // cookies == null
            // ***** Create Cookies *****
            Cookie username = new Cookie("username", "cookie");
            username.setMaxAge(3600 /* seconds */);  // persistent storage (TTL = 3600s)
            // The 'username' cookie will be carried
            // ONLY when requesting http://127.0.0.1:8080/serve/hello
            username.setPath("/serve/hello");
            Cookie password = new Cookie("password", "1024");

            // ***** Add Cookies to Response Headers *****
            resp.addCookie(username); // resp.setHeader("Set-Cookie", "username=cookie");
            resp.addCookie(password); // resp.setHeader("Set-Cookie", "password=1024");
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write(builder.toString());
    }
}
