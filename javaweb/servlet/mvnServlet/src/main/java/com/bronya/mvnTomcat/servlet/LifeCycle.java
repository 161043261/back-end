package com.bronya.mvnTomcat.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
public class LifeCycle extends HttpServlet {

    public LifeCycle() {
        System.out.println("construct");
    }

    @Override
    public void init() throws ServletException {
        System.out.println("init");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("service");
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
