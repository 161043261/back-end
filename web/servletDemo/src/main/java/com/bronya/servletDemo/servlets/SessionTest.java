package com.bronya.servletDemo.servlets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/session")
public class SessionTest extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> paramMap = req.getParameterMap();
        HttpSession session = req.getSession();
        if (!session.isNew()) {
            System.out.println("session is NOT new");
            // (String) session.getAttribute("account");
            System.out.println((String) session.getAttribute("account"));
        } else { // session.isNew() == true
            System.out.println("session is new");
            session.setMaxInactiveInterval(120 /* seconds */);
            session.setAttribute("account", paramMap.get("account")[0]);
        }
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();
        writer.write("JSESSIONID: " + session.getId() + "<br>");

        /* Append: set servletContext attributes (use firefox) */
        ServletContext context = this.getServletContext();

        String serverName = (String) context.getAttribute("serverName");
        if (serverName != null) {
            writer.write("serverName: " + serverName);
        }
    }
}
