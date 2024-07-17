package com.bronya.servletDemo.listeners;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * <a href="http://127.0.0.1:8080/demo/session?company=bronya">SessionDemo</a>
 */
public class HttpSessionListenerDemo implements HttpSessionListener, HttpSessionAttributeListener {
    @Override // The creation of any httpSession triggers to invoke the sessionCreated method
    public void sessionCreated(HttpSessionEvent se) {
    }

    @Override // The destruction of any httpSession triggers to invoke the sessionDestroyed method
    public void sessionDestroyed(HttpSessionEvent se) {
    }

    @Override // HttpSessionAttribute insert
    public void attributeAdded(HttpSessionBindingEvent se) {
    }

    @Override // HttpSessionAttribute delete
    public void attributeRemoved(HttpSessionBindingEvent se) {
    }

    @Override // HttpSessionAttribute -> update ->
    public void attributeReplaced(HttpSessionBindingEvent se) {
    }
}
