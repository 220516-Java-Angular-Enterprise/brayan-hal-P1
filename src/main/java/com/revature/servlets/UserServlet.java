package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.requests.NewUserRequest;
import com.revature.models.Users;
import com.revature.services.UserService;
import com.revature.util.annotations.Inject;
import com.revature.util.exceptions.InvalidRequestException;
import com.revature.util.exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    @Inject
    private final ObjectMapper objectMapper;
    private final UserService userService;

    @Inject
    public UserServlet(ObjectMapper objectMapper, UserService userService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            NewUserRequest request = objectMapper.readValue(req.getInputStream(), NewUserRequest.class);
            Users newUser = userService.register(request);
            resp.setStatus(201);
            resp.setContentType("application/json");
            resp.getWriter().write(objectMapper.writeValueAsString(newUser.getUser_id()));
        }catch(InvalidRequestException e){
            resp.setStatus(404);
        }catch(ResourceConflictException e){
            resp.setStatus(409);
        }catch(Exception e){
            resp.setStatus(500);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
