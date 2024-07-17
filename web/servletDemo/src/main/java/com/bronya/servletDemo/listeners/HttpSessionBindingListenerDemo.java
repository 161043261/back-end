package com.bronya.servletDemo.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

import static com.bronya.servletDemo.util.Colors.RESET;
import static com.bronya.servletDemo.util.Colors.YELLOW;

@WebListener
public class HttpSessionBindingListenerDemo implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println(YELLOW + "The bound between a httpSessionListener and a httpSession triggers to invoke the valueBound method." + RESET);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println(YELLOW + "The unbound between a httpSessionListener and a httpSession triggers to invoke the valueUnbound method." + RESET);
    }
}
