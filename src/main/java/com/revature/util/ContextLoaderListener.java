package com.revature.util;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.revature.dao.ReimbursementDAO;
import com.revature.dao.UserDAO;

import com.revature.services.ReimbursementService;

import com.revature.dao.AdminDAO;
import com.revature.dao.UserDAO;
import com.revature.services.AdminServices;

import com.revature.services.TokenService;
import com.revature.services.UserService;
import com.revature.servlets.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\nWeb App created***********************");

        //Mapper is used for writing or reading to/from a json file to or from POJOs
        ObjectMapper objectMapper = new ObjectMapper();

        //Dependency injection to initialize user servlet
        UserServlet userServlet = new UserServlet(objectMapper, new UserService(new UserDAO()), new TokenService(new JwtConfig()));
        AuthServlet authServlet = new AuthServlet(objectMapper, new UserService(new UserDAO()), new TokenService(new JwtConfig()));
        HistoryServlet historyServlet = new HistoryServlet(objectMapper, new ReimbursementService(new ReimbursementDAO()), new TokenService(new JwtConfig()));
        ReimburseServlet reimburseServlet = new ReimburseServlet(objectMapper, new ReimbursementService(new ReimbursementDAO()), new TokenService(new JwtConfig()));

        
  AdminServlet adminServlet = new AdminServlet(objectMapper, new AdminServices(new AdminDAO()), new TokenService(new JwtConfig()));


        /* Need ServletContext class to map whatever servlet to url path. */
        ServletContext context = sce.getServletContext();
        context.addServlet("UserServlet", userServlet).addMapping("/users/*");
        context.addServlet("AuthServlet", authServlet).addMapping("/auth");


        context.addServlet("ReimburseServlet",reimburseServlet).addMapping("/reimbursement/*");
        context.addServlet("HistoryServlet", historyServlet).addMapping("/history/*");

        
  context.addServlet("AdminServlet", adminServlet).addMapping("/admin/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\nWeb App Destroyed***********************");
    }
}
