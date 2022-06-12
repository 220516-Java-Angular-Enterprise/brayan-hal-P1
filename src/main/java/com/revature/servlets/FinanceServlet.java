package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.responses.Principle;
import com.revature.services.FinanceService;
import com.revature.services.TokenService;
import com.revature.util.annotations.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FinanceServlet extends HttpServlet {
    @Inject
    private final TokenService tokenService;
    private final FinanceService financeService;
    private final ObjectMapper objectMapper;

    @Inject
    public FinanceServlet(TokenService tokenService, FinanceService financeService, ObjectMapper objectMapper) {
        this.tokenService = tokenService;
        this.financeService = financeService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Principle requester = tokenService.extractRequesterDetails(resp.getHeader("Authorization"));

        if(requester==null){
            resp.setStatus(403);
            return;
        }

        if(!requester.getRole().equals("FINANCE")){
            resp.setStatus(401);
            return;
        }


    }
}
