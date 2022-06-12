package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.responses.Principle;
import com.revature.models.Reimbursements;
import com.revature.services.ReimbursementService;
import com.revature.services.TokenService;
import com.revature.util.annotations.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HistoryServlet extends HttpServlet {

    @Inject
    private final ObjectMapper mapper;
    private final ReimbursementService reimbursementService;
    private final TokenService tokenService;


    public HistoryServlet(ObjectMapper mapper, ReimbursementService reimbursementService, TokenService tokenService) {
        this.mapper = mapper;
        this.reimbursementService = reimbursementService;
        this.tokenService = tokenService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principle requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));

        if(requester == null){
            resp.setStatus(401);//unathorized
            return;
        }

        if(requester.getRole().equals("ADMIN")){
            resp.setStatus(403);//forbidden
            return;
        }
        List<Reimbursements> history = reimbursementService.getAllUserRs(requester.getUsername());
        resp.setContentType("application/json");
        resp.getWriter().write(mapper.writeValueAsString(history));
    }
}
