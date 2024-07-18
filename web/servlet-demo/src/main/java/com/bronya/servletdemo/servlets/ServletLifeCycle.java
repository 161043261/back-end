package com.bronya.servletdemo.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet (singleton) Life Cycle
 * Constructing <- constructor()
 * Initializing <- init()
 * Servicing <- service()
 * Destroying <- destroy()
 */
@WebServlet(urlPatterns = "/life/cycle")
public class ServletLifeCycle extends HttpServlet {

    public ServletLifeCycle() {
        System.out.println("Constructing Servlet..."); // Invoked when the 1st request is received
    }

    @Override
    public void init() {
        System.out.println("Initializing Servlet...");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet Serving...");
    }

    @Override
    public void destroy() {
        System.out.println("Destroying Servlet...");
    }
}
