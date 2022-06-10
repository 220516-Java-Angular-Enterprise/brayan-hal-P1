package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.requests.NewReimburseRequest;
import com.revature.dtos.responses.Principle;
import com.revature.models.Reimbursements;
import com.revature.services.ReimbursementService;
import com.revature.services.TokenService;
import com.revature.util.annotations.Inject;
import com.revature.util.exceptions.InvalidRequestException;
import com.revature.util.exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReimburseServlet extends HttpServlet {
    @Inject
    private final ObjectMapper mapper;
    private final ReimbursementService reimbursementService;
    private final TokenService tokenService;

    public ReimburseServlet(ObjectMapper mapper, ReimbursementService reimbursementService, TokenService tokenService) {
        this.mapper = mapper;
        this.reimbursementService = reimbursementService;
        this.tokenService  = tokenService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            NewReimburseRequest request = mapper.readValue(req.getInputStream(), NewReimburseRequest.class);
            Reimbursements newReimburse = reimbursementService.saveReimbursement(request);
            resp.setStatus(201);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(newReimburse.getStatus_id()));
        }catch (InvalidRequestException e){
            resp.setStatus(404);
        }catch (ResourceConflictException e){
            resp.setStatus(409);
        }
        catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Principle requester = tokenService.extractRequesterDetails(req.getHeader("Authorization?"));
//        List<Reimbursements>reimbursements = reimbursementService.getRemByUser(requester.getUser_id());
//        resp.setContentType("application/json");
//        resp.getWriter().write(mapper.writeValueAsString(reimbursements));
//        //resp.getWriter().write("<h1>New Reimbursement</h1>");
//    }
}
