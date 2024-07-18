package com.bronya.servletdemo.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

import static com.bronya.servletdemo.util.Colors.PURPLE;
import static com.bronya.servletdemo.util.Colors.RESET;

@WebListener
public class ServletRequestListenerDemo implements ServletRequestListener, ServletRequestAttributeListener {
    @Override // The creation of any servletRequest triggers to invoke the requestInitialized method
    public void requestInitialized(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        System.out.println(PURPLE + "RequestID=" + servletRequest.getRequestId() + " ServletRequest Initialized" + RESET);
    }

    @Override // The destruction of any servletRequest triggers to invoke the requestDestroyed method
    public void requestDestroyed(ServletRequestEvent sre) {
        ServletRequest servletRequest = sre.getServletRequest();
        System.out.println(PURPLE + "RequestID=" + servletRequest.getRequestId() + " ServletRequest Destroyed" + RESET);
    }

    @Override // ServletRequestAttribute insert
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        ServletRequest servletRequest = srae.getServletRequest();
        String key = srae.getName();
        Object value = srae.getValue();
        System.out.println(PURPLE + "RequestID=" + servletRequest.getRequestId() + " ServletRequestAttribute " // insert
                + key + ": " + value + "Added" + RESET);
    }

    @Override // ServletRequestAttribute delete
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        ServletRequest servletRequest = srae.getServletRequest();
        String key = srae.getName();
        Object value = srae.getValue();
        System.out.println(PURPLE + "RequestID=" + servletRequest.getRequestId() + " ServletRequestAttribute " // delete
                + key + ": " + value + "Removed" + RESET);
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        ServletRequest servletRequest = srae.getServletRequest();
        String key = srae.getName();
        Object value = srae.getValue();
        Object newValue = servletRequest.getAttribute(key);
        System.out.println(PURPLE + "RequestID=" + servletRequest.getRequestId() + " ServletRequestAttribute " // update
                + key + ": " + value + "--Replaced->" + newValue + RESET);
    }
}
