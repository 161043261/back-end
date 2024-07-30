package com.bronya.servletdemo.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionActivationListener;
import jakarta.servlet.http.HttpSessionEvent;

@WebListener
public class HttpSessionActivationListenerDemo implements HttpSessionActivationListener {
    @Override
    public void sessionWillPassivate(HttpSessionEvent se) {
        System.out.println("The passivation of a httpSession triggers to invoke the sessionWillPassivate method.");
    }

    @Override
    public void sessionDidActivate(HttpSessionEvent se) {
        System.out.println("The activation of a httpSession triggers to the sessionDidActivate method.");
    }
}
