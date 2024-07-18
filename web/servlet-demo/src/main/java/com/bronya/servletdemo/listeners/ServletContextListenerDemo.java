package com.bronya.servletdemo.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;

import static com.bronya.servletdemo.util.Colors.BLUE;
import static com.bronya.servletdemo.util.Colors.RESET;

@WebListener
public class ServletContextListenerDemo implements ServletContextListener, ServletContextAttributeListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println(BLUE + "hash=" + servletContext.hashCode() + " ServletContext Initialized" + RESET);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        System.out.println(BLUE + "hash=" + servletContext.hashCode() + " ServletContext Destroyed" + RESET);
    }

    @Override
    public void attributeAdded(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println(BLUE + "hash=" + servletContext.hashCode() + " ServletContextAttribute " // insert
                + key + ": " + value + " Added" + RESET);
    }

    @Override // delete
    public void attributeRemoved(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        System.out.println(BLUE + "hash=" + servletContext.hashCode() + " ServletContextAttribute " // delete
                + key + ": " + value + " Removed" + RESET);
    }

    @Override // update
    public void attributeReplaced(ServletContextAttributeEvent scae) {
        ServletContext servletContext = scae.getServletContext();
        String key = scae.getName();
        Object value = scae.getValue();
        Object newValue = servletContext.getAttribute(key);
        System.out.println(BLUE + "hash=" + servletContext.hashCode() + " ServletContextAttribute " // update
                + key + ": " + value + "--Replaced->" + newValue + RESET);
    }
}
