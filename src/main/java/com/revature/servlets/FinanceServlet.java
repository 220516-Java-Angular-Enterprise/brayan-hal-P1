package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.requests.ChangeStatusRequest;
import com.revature.dtos.responses.Principle;
import com.revature.dtos.responses.ReimbursementStatus;
import com.revature.services.FinanceService;
import com.revature.services.TokenService;
import com.revature.util.annotations.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class FinanceServlet extends HttpServlet {
    @Inject
    private final TokenService tokenService;
    private final FinanceService financeService;
    private final ObjectMapper objectMapper;

    @Inject

    public FinanceServlet(ObjectMapper objectMapper, FinanceService financeService, TokenService tokenService) {
        this.tokenService = tokenService;
        this.financeService = financeService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principle requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        List<String> path = Arrays.asList(req.getRequestURI().split("/"));

        if(requester==null){
            resp.setStatus(403);
            return;
        }

        if(!requester.getRole().equals("FINANCE")){
            resp.setStatus(401);
            return;
        }

        if(path.contains("finance") && path.contains("status")){
            if(path.contains("pending")){
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(financeService.getByStatus("pending")));
            }else if(path.contains("denied")){
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(financeService.getByStatus("denied")));
            }else if(path.contains("approved")){
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(financeService.getByStatus("approved")));
            }
        }else if(path.contains("finance") && path.contains("type")){
            if(path.contains("lodging")){
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(financeService.getByType("lodging")));
            }else if(path.contains("food")){
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(financeService.getByType("food")));
            }else if(path.contains("travel")){
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(financeService.getByType("travel")));
            }else if(path.contains("other")){
                resp.setContentType("application/json");
                resp.getWriter().write(objectMapper.writeValueAsString(financeService.getByType("other")));
            }
        }else{
            resp.setStatus(404);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principle requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
        List<String> path = Arrays.asList(req.getRequestURI().split("/"));
        System.out.println();
        if(requester==null){
            resp.setStatus(403);
            return;
        }

        if(!requester.getRole().equals("FINANCE")){
            resp.setStatus(401);
            return;
        }
        if(path.contains("finance") && path.contains("changestatus")){
            ChangeStatusRequest changeStatusRequest = objectMapper.readValue(req.getInputStream(), ChangeStatusRequest.class);
            financeService.changeStatus(changeStatusRequest, requester.getUser_id());
            resp.setContentType("application/json");
            resp.getWriter().write(objectMapper.writeValueAsString(changeStatusRequest.getStatus()));
        }
    }
}
