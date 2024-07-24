package com.bronya.servletdemo.listeners;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        var httpSessionBindingListenerDemo = new HttpSessionBindingListenerDemo();
        // The bound between a httpSessionListener and a httpSession triggers to
        // invoke the ./HttpSessionBindingListenerDemo.java valueBound method.
        session.setAttribute("BindingListener", httpSessionBindingListenerDemo); // bound

        // The unbound between a httpSessionListener and a httpSession triggers to
        // invoke the ./HttpSessionBindingListenerDemo.java valueUnbound method.
        session.removeAttribute("BindingListener"); // unbound

        var httpSessionActivationListenerDemo = new HttpSessionActivationListenerDemo();
        // The passivation of a httpSession triggers to
        // invoke the ./HttpSessionActivationListenerDemo.java sessionWillPassivate method.

        // The activation of a httpSession triggers to
        // invoke the ./HttpSessionActivationListenerDemo.java sessionDidActivate method.
        session.setAttribute("ActivationListener", httpSessionActivationListenerDemo);

        session.invalidate();
    }
}
