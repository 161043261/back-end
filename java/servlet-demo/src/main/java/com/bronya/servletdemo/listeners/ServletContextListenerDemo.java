package com.bronya.servletdemo.listeners;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerDemo implements ServletContextListener, ServletContextAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println("hash=" + servletContext.hashCode() + " ServletContext Initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println("hash=" + servletContext.hashCode() + " ServletContext Destroyed");
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println("hash=" + servletContext.hashCode() + " ServletContextAttribute " // insert
                + key + ": " + value + " Added");
    }

    @Override // delete
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println("hash=" + servletContext.hashCode() + " ServletContextAttribute " // delete
                + key + ": " + value + " Removed");
    }

    @Override // update
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        Object newValue = servletContext.getAttribute(key);
        System.out.println("hash=" + servletContext.hashCode() + " ServletContextAttribute " // update
                + key + ": " + value + "--Replaced->" + newValue);
    }
}
