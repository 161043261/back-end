package com.bronya.servletdemo.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/resource/path")
public class ResourcePath extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        ServletContext servletContext = getServletContext(); // this.getServletContext();

        String webapp = servletContext.getRealPath("./");
        System.out.println(webapp);
        // cd ./src/webapp
        // servletContext.getRealPath("./") == pwd

        String statik = servletContext.getRealPath("./static");
        System.out.println(statik);
        // cd ./src/webapp/static
        // servletContext.getRealPath("./static") == pwd

        String webInformation = servletContext.getRealPath("./WEB-INF");
        System.out.println(webInformation);
        // cd ./src/webapp/WEB-INF
        // servletContext.getRealPath("./WEB-INF") == pwd

        String contextPath = servletContext.getContextPath();
        System.out.println(contextPath); // /demo
    }
}
