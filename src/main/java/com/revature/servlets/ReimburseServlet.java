package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dtos.requests.ActiveRequest;
import com.revature.dtos.requests.ChangePassRequest;
import com.revature.dtos.requests.NewReimburseRequest;
import com.revature.dtos.requests.UpdatePendingRequest;
import com.revature.dtos.responses.Principle;
import com.revature.models.Reimbursements;
import com.revature.models.Users;
import com.revature.services.ReimbursementService;
import com.revature.services.TokenService;
import com.revature.util.annotations.Inject;
import com.revature.util.exceptions.AuthenticationException;
import com.revature.util.exceptions.InvalidRequestException;
import com.revature.util.exceptions.ResourceConflictException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
//creates new reimbursement form
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            NewReimburseRequest request = mapper.readValue(req.getInputStream(), NewReimburseRequest.class);
            Principle requestor = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
            //check authorization
            if (requestor == null){
                resp.setStatus(401);
                return;
            }
            if (!requestor.getRole().equals("DEFAULT")){
                resp.setStatus(403);
                return;
            }
            Reimbursements newReimburse = reimbursementService.saveReimbursement(request);
            resp.setStatus(201);
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(newReimburse.getStatus_id()));
        }catch (InvalidRequestException e){
            resp.setStatus(404);
        }
        catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Principle requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
            if(requester == null){
                resp.setStatus(401);//unathorized
                return;
            }
            if(requester.getRole().equals("ADMIN")){
                resp.setStatus(403);//forbidden
                return;
            }
            if(req.getPathInfo().equals("/edit-reimbursement")){
                UpdatePendingRequest updatePendingRequest = mapper.readValue(req.getInputStream(), UpdatePendingRequest.class);
                reimbursementService.updatePending(updatePendingRequest);
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(updatePendingRequest.getDescription()));
            }
        }catch (InvalidRequestException e){
            resp.setStatus(404);
        }catch (AuthenticationException e){
            resp.setStatus(401);
        }
        catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

    //view pending reimbursement
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Principle requester = tokenService.extractRequesterDetails(req.getHeader("Authorization"));
            if (requester == null){
                resp.setStatus(401); // UNAUTHORIZED
                return;
            }
            if (requester.getRole().equals("ADMIN")) {
                resp.setStatus(403); //forbidden
                //Admin are not allowed to look at these
                return;
            }
            if (requester.getRole().equals("FINANCIAL MANAGER")) { // if Financial Manager then can View all pending
                List <Reimbursements> allPending = reimbursementService.getByStatus("P");
                resp.setContentType("application/json");
                resp.getWriter().write(mapper.writeValueAsString(allPending));
                return;
            }

            List<Reimbursements> pendingReimburse = reimbursementService.getPendingByUser(requester.getUsername());
            //pendingReimburse = pendingReimburse.stream().sorted(Comparator.comparing(Reimbursements::getSubmitted)).collect(Collectors.toList());
            resp.setContentType("application/json");
            resp.getWriter().write(mapper.writeValueAsString(pendingReimburse));
        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
        }
    }

}
