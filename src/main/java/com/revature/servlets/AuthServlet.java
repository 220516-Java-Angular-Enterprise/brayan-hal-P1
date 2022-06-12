package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.requests.LoginRequest;
import com.revature.dtos.responses.Principle;
import com.revature.services.TokenService;
import com.revature.services.UserService;
import com.revature.util.annotations.Inject;
import com.revature.util.exceptions.AuthenticationException;
import com.revature.util.exceptions.InvalidRequestException;
import jdk.nashorn.internal.parser.Token;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final UserService userService;
    private final TokenService tokenService;

    public AuthServlet(ObjectMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
        tokenService = null;
    }

    @Inject
    public AuthServlet(ObjectMapper mapper, UserService userService, TokenService tokenService) {
        this.mapper = mapper;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            LoginRequest request = mapper.readValue(req.getInputStream(), LoginRequest.class);
            Principle principle = new Principle(userService.Login(request));
            if(!principle.isIs_active()) {resp.setStatus(403); return;}
            String token = tokenService.generateToken(principle);
            resp.setHeader("Authorization",token);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(principle));
        }catch (InvalidRequestException e){
            resp.setStatus(404);
        }
        catch (AuthenticationException e){
            resp.setStatus(401);
        }
        catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("<h1>Authorize</h1>");
    }
}
