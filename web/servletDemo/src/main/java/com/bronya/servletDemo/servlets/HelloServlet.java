package com.bronya.servletDemo.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        req.getParameterMap().forEach((name, value) -> writer.write(name + ": " + Arrays.toString(value) + ", "));
        writer.write("<h1>Hello World</h1>");
        writer.flush(); // this can be omitted
        writer.close(); // this can be omitted
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
