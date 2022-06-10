package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.requests.NewUserRequest;
import com.revature.dtos.responses.Principle;
import com.revature.models.Users;
import com.revature.services.TokenService;
import com.revature.services.UserService;
import com.revature.util.annotations.Inject;
import com.revature.util.exceptions.InvalidRequestException;
import com.revature.util.exceptions.ResourceConflictException;
import jdk.nashorn.internal.parser.Token;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Inject
    private final ObjectMapper objectMapper;
    private final UserService userService;
    private final TokenService tokenService;

    @Inject
    public UserServlet(ObjectMapper objectMapper, UserService userService, TokenService tokenService) {
        this.objectMapper = objectMapper;
        this.userService = userService;
        this.tokenService = tokenService;
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
        Principle requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        if(requester == null){
            resp.setStatus(401);//unathorized
            return;
        }

        if(!requester.getRole().equals("ADMIN")){
            resp.setStatus(403);//forbidden
            return;
        }


        List<Users> users = userService.getAllUsers();
        resp.setContentType("application/json");
        resp.getWriter().write(objectMapper.writeValueAsString(users));
    }
}
