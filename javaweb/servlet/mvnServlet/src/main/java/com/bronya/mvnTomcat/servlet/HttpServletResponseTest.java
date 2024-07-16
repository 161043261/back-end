package com.bronya.mvnTomcat.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@WebServlet("/resp")
public class HttpServletResponseTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Response Line
        resp.setStatus(200);

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
