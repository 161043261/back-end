package com.bronya.servletDemo.listeners;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;

public class ServletRequestListenerDemo implements ServletRequestListener, ServletRequestAttributeListener {
    @Override // The creation of any servletRequest triggers to invoke the requestInitialized method
    public void requestInitialized(ServletRequestEvent sre) {
    }

    @Override // The destruction of any servletRequest triggers to invoke the requestDestroyed method
    public void requestDestroyed(ServletRequestEvent sre) {
    }

    @Override // ServletRequestAttribute insert
    public void attributeAdded(ServletRequestAttributeEvent srae) {
    }

    @Override // ServletRequestAttribute delete
    public void attributeRemoved(ServletRequestAttributeEvent srae) {
    }

    @Override // ServletRequestAttribute -> update ->
    public void attributeReplaced(ServletRequestAttributeEvent srae) {
    }
}
