package com.bronya.servletdemo.listeners;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

/**
 * <a href="http://127.0.0.1:8080/demo/session?company=bronya">SessionDemo</a>
 */
@WebListener
public class HttpSessionListenerDemo implements HttpSessionListener, HttpSessionAttributeListener {
    @Override // The creation of any httpSession triggers to invoke the sessionCreated method
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        System.out.println("JSESSIONID=" + httpSession.getId() + " HttpSession Created");
    }

    @Override // The destruction of any httpSession triggers to invoke the sessionDestroyed method
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession httpSession = se.getSession();
        System.out.println("JSESSIONID=" + httpSession.getId() + " HttpSession Destroyed");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        HttpSession httpSession = se.getSession();
        String key = se.getName();
        Object value = se.getValue();
        System.out.println("JSESSIONID=" + httpSession.getId() + " HttpSessionAttribute " // insert
                + key + ": " + value + " Added");

    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        HttpSession httpSession = se.getSession();
        String key = se.getName();
        Object value = se.getValue();
        System.out.println("JSESSIONID=" + httpSession.getId() + " HttpSessionAttribute "  // delete
                + key + ": " + value + " Removed");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        HttpSession httpSession = se.getSession();
        String key = se.getName();
        Object value = se.getValue();
        Object newValue = httpSession.getAttribute(key);
        System.out.println("JSESSIONID=" + httpSession.getId() + " HttpSessionAttribute " // update
                + key + ": " + value + "--Replaced->" + newValue);
    }
}
