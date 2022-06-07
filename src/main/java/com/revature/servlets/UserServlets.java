package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.util.annotations.Inject;

import javax.servlet.http.HttpServlet;

public class UserServlets extends HttpServlet {
    @Inject
    private final ObjectMapper objectMapper;
}
