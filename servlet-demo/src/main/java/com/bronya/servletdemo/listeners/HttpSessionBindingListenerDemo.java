package com.bronya.servletdemo.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

@WebListener
public class HttpSessionBindingListenerDemo implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("The bound between a httpSessionListener and a httpSession triggers to invoke the valueBound method.");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("The unbound between a httpSessionListener and a httpSession triggers to invoke the valueUnbound method.");
    }
}
