package com.bronya.servletDemo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * *------------ Servlet is Singleton ------------*
 * | Stages of the Servlet Life Cycle             |
 * *----------------------------------------------*
 * | 1 | Loading a Servlet        | constructor() |
 * | 2 | Initializing the Servlet | init()        |
 * | 3 | Request Handling         | service()     |
 * | 4 | Destroying the Servlet   | destroy()     |
 * *----------------------------------------------*
 */
@WebServlet(value = "/lifecycle", loadOnStartup = -1) // redundant
public class ServletLifeCycle extends HttpServlet {

    public ServletLifeCycle() {
        System.out.println("Constructing...");
    }

    @Override
    public void init() {
        System.out.println("Initializing...");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Serving...");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying");
    }
}
