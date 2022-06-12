package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.AdminDAO;
import com.revature.dtos.requests.ActiveRequest;
import com.revature.dtos.requests.ChangePassRequest;
import com.revature.dtos.responses.Principle;
import com.revature.services.AdminServices;
import com.revature.services.TokenService;
import com.revature.util.annotations.Inject;
import com.revature.util.exceptions.AuthenticationException;
import com.revature.util.exceptions.InvalidRequestException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AdminServlet extends HttpServlet {
    @Inject
    private final ObjectMapper objectMapper;
    private final AdminServices adminServices;
    private final TokenService tokenService;

    @Inject
    public AdminServlet(ObjectMapper objectMapper, AdminServices adminServices, TokenService tokenService) {
        this.objectMapper = objectMapper;
        this.adminServices = adminServices;
        this.tokenService = tokenService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            System.out.println(req.getRequestURI());
            Principle requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
            if(requester == null){
                resp.setStatus(401);//unathorized
                return;
            }
            if(!requester.getRole().equals("ADMIN")){
                resp.setStatus(403);//forbidden
                return;
            }


            if(req.getPathInfo().equals("/status")){
                ActiveRequest activeRequest = objectMapper.readValue(req.getInputStream(), ActiveRequest.class);
                adminServices.changeStatus(activeRequest);
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(activeRequest.isActive()));
            }
            else if(req.getPathInfo().equals("/password")){
                ChangePassRequest changePassRequest = objectMapper.readValue(req.getInputStream(), ChangePassRequest.class);
                adminServices.changePassword(changePassRequest);
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(changePassRequest.getPassword()));
            }


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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
