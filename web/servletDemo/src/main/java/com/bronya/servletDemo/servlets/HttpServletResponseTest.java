package com.bronya.servletDemo.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * resp.setStatus();
 * resp.setHeader();
 * resp.setContentType();
 * resp.setContentLength();
 * resp.getWriter();
 * resp.getOutputStream();
 */
@WebServlet("/response")
public class HttpServletResponseTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Response Line
        resp.setStatus(HttpServletResponse.SC_OK); // 200

        // Response Headers
        resp.setHeader("Content-Type", "text/html");
        resp.setContentType("text/html");
        String responseBody = "Response Body";
        // getBytes(): String => byte[]
        resp.setContentLength(responseBody.getBytes().length);

        // Response Body
        PrintWriter writer = resp.getWriter(); // character output stream
        writer.write(responseBody);
        // ServletOutputStream stream = resp.getOutputStream(); // byte output stream
    }
}
