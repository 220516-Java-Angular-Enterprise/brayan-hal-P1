package com.revature.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.UserDAO;
import com.revature.services.UserService;
import com.revature.servlets.UserServlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\nWeb App created");

        ObjectMapper objectMapper = new ObjectMapper();

        UserServlet userServlet = new UserServlet(objectMapper, new UserService(new UserDAO()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
