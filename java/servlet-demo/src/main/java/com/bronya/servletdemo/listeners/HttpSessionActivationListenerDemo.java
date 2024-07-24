package com.bronya.servletdemo.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionEvent;

import static com.bronya.servletdemo.util.Colors.RESET;
import static com.bronya.servletdemo.util.Colors.YELLOW;

@WebListener
public class HttpSessionActivationListenerDemo implements HttpSessionActivationListener {
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println(YELLOW + "The passivation of a httpSession triggers to invoke the sessionWillPassivate method." + RESET);
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println(YELLOW + "The activation of a httpSession triggers to the sessionDidActivate method." + RESET);
    }
}
