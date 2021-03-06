package com.revature.services;

import com.revature.dao.ReimbursementDAO;
import com.revature.dtos.requests.NewReimburseRequest;
import com.revature.dtos.responses.UserReimburse;
import com.revature.models.Reimbursements;
import com.revature.util.annotations.Inject;
import com.revature.util.exceptions.ResourceConflictException;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class ReimbursementService {
    @Inject
    private final ReimbursementDAO reimbursementDAO;

    @Inject
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public Reimbursements saveReimbursement(NewReimburseRequest request){
        long now = System.currentTimeMillis();
        Reimbursements reimbursements = new Reimbursements(request.getAmount(),request.getDescription(),request.getType_id(), request.getAuthor_id());
        reimbursements.setReimb_id(UUID.randomUUID().toString());
        reimbursements.setStatus_id("tUwkJ7T8wA");
        reimbursements.setSubmitted(new Date(now));
        reimbursementDAO.save(reimbursements);
        return reimbursements;
    }
    public List<Reimbursements> getAll(){ return reimbursementDAO.getAll();}
    public List<Reimbursements> getByType(String type_id){return reimbursementDAO.getReimburseByType(type_id);}
    public List<Reimbursements> getByStatus(String status_id){return reimbursementDAO.getReimburseByStatus(status_id);}
    public List<Reimbursements> getDetailsByRemID(String reimb_id){
        if(reimb_id == null){
            throw new ResourceConflictException();
        }
        return reimbursementDAO.getDetailsByReimburseID(reimb_id);
    }
    public List<UserReimburse> getUserPending(String author_id){
        if(author_id == null){
            throw new ResourceConflictException();
        }
        return  reimbursementDAO.getPendingByUser(author_id);}
    public List<UserReimburse> getUserReimburseAll(String author_id){
        if(author_id == null){
            throw new ResourceConflictException();
        }
        return reimbursementDAO.getAllByUser(author_id);}
    public List<UserReimburse> getUsersNewFirst(String author_id){
        if(author_id == null){
            throw new ResourceConflictException();
        }return reimbursementDAO.getUserAllNewFirst(author_id);}
    public List<UserReimburse> getPendingNewFirst (String author_id){
        if(author_id == null){
            throw new ResourceConflictException();
        }return reimbursementDAO.getNewPendingFirst(author_id);}
    public void updatePending (double amount, String reimb_id, String description){
        if(reimb_id == null){
            throw new ResourceConflictException();
        }
        if(description == null){
            throw new ResourceConflictException();
        }
        reimbursementDAO.updatePendingReimburse(amount,reimb_id,description);}

}
