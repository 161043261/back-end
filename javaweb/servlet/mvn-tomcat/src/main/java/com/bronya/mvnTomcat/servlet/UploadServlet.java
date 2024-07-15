package com.bronya.mvnTomcat.servlet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ServletContext servletContext = getServletContext(); // this.getServletContext();

        String webapp = servletContext.getRealPath("./");
        System.out.println(webapp);
        // cd ./src/webapp
        // servletContext.getRealPath("./upload") == pwd

        String upload = servletContext.getRealPath("./upload");
        System.out.println(upload);
        // cd ./src/webapp/upload
        // servletContext.getRealPath("./upload") == pwd

        String contextPath = servletContext.getContextPath();
        System.out.println("".equals(contextPath) ? "empty string" : contextPath); // empty string

        // try-with-resource
        try (FileOutputStream stream = new FileOutputStream(upload + "/parameters.txt")) {
            // do nothing
        }
    }
}
