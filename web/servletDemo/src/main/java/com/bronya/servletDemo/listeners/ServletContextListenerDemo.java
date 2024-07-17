package com.bronya.servletDemo.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

import static com.bronya.servletDemo.util.Colors.BLUE;
import static com.bronya.servletDemo.util.Colors.RESET;

@WebListener
public class ServletContextListenerDemo implements ServletContextListener, ServletContextAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println(BLUE + servletContext.hashCode() + " ServletContext Initialized" + RESET);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println(BLUE + servletContext.hashCode() + " ServletContext Destroyed" + RESET);
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println(BLUE + servletContext.hashCode() + " ServletContextAttribute " // insert
                + key + ": " + value + " Added" + RESET);
    }

    @Override // delete
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println(BLUE + servletContext.hashCode() + " ServletContextAttribute " // insert
                + key + ": " + value + " Removed" + RESET);
    }

    @Override // update
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        Object newValue = servletContext.getAttribute(key);
        System.out.println(BLUE + servletContext.hashCode() + " ServletContextAttribute " // update
                + key + ": " + value + " -> Replaced -> " + key + ": " + newValue + RESET);
    }
}
