package com.revature.services;

import com.revature.dao.FinanceDAO;

import com.revature.dtos.requests.ChangeStatusRequest;
import com.revature.dtos.responses.ReimbursementStatus;
import com.revature.dtos.responses.ReimbursementType;
import com.revature.util.annotations.Inject;
import com.revature.util.exceptions.InvalidRequestException;
import com.revature.util.exceptions.ResourceConflictException;

import java.util.List;


public class FinanceService {
    @Inject
    private final FinanceDAO financeDAO;

    @Inject
    public FinanceService(FinanceDAO financeDAO) {
        this.financeDAO = financeDAO;
    }

    public List<ReimbursementType> getByType(String type){
        switch(type){
            case "lodging":
                return financeDAO.getAllLodging();
            case "food":
                return financeDAO.getAllFood();
            case "other":
                return financeDAO.getAllOther();
            case "travel":
                return financeDAO.getAllTravel();
            default:
                throw new ResourceConflictException();
        }
    }

    public List<ReimbursementStatus> getByStatus(String status){
        switch(status){
            case "pending":
                return financeDAO.getAllPending();
            case "denied":
                return financeDAO.getAllDeclined();
            case "approved":
                return financeDAO.getAllApproved();
            default:
                throw new ResourceConflictException();
        }
    }

    public void changeStatus(ChangeStatusRequest changeStatusRequest, String user_id){
        financeDAO.changeStatus(changeStatusRequest.getStatus(), changeStatusRequest.getReimb_id(), user_id);
    }
}
