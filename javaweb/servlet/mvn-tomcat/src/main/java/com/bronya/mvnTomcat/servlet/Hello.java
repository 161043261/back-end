package com.bronya.mvnTomcat.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

// value equals to urlPatterns
@WebServlet(name = "helloServlet ", value = "/hello") // urlPatterns = {"/hello"}
public class HelloServlet extends HttpServlet {

    private void handle(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html"); // resp.setHeader("Content-Type", "text/html");
        resp.setStatus(HttpServletResponse.SC_OK); // StatusCode_OK
        PrintWriter writer = resp.getWriter();
        // req.getParameter("key");
        req.getParameterMap().forEach((name, value) -> writer.write(name + ": " + Arrays.toString(value) + ", "));
        writer.write("<h1>Hello World</h1>");
        writer.flush(); // can be omitted
        writer.close(); // can be omitted
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        handle(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        handle(req, resp);
    }
}
