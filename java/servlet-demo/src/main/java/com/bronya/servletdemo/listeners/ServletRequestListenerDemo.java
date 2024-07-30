package com.bronya.servletdemo.listeners;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletRequestListenerDemo implements ServletRequestListener, ServletRequestAttributeListener {
    @Override // The creation of any servletRequest triggers to invoke the requestInitialized method
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        System.out.println("RequestID=" + servletRequest.getRequestId() + " ServletRequest Initialized");
    }

    @Override // The destruction of any servletRequest triggers to invoke the requestDestroyed method
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        System.out.println("RequestID=" + servletRequest.getRequestId() + " ServletRequest Destroyed");
    }

    @Override // ServletRequestAttribute insert
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        ServletRequest servletRequest = srae.getServletRequest();
        String key = srae.getName();
        Object value = srae.getValue();
        System.out.println("RequestID=" + servletRequest.getRequestId() + " ServletRequestAttribute " // insert
                + key + ": " + value + "Added");
    }

    @Override // ServletRequestAttribute delete
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        ServletRequest servletRequest = srae.getServletRequest();
        String key = srae.getName();
        Object value = srae.getValue();
        System.out.println("RequestID=" + servletRequest.getRequestId() + " ServletRequestAttribute " // delete
                + key + ": " + value + "Removed");
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        ServletRequest servletRequest = srae.getServletRequest();
        String key = srae.getName();
        Object value = srae.getValue();
        Object newValue = servletRequest.getAttribute(key);
        System.out.println("RequestID=" + servletRequest.getRequestId() + " ServletRequestAttribute " // update
                + key + ": " + value + "--Replaced->" + newValue);
    }
}
