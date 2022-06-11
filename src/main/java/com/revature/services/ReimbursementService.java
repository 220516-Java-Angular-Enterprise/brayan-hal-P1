package com.revature.services;

import com.revature.dao.ReimbursementDAO;
import com.revature.dtos.requests.NewReimburseRequest;
import com.revature.dtos.responses.Principle;
import com.revature.models.Reimbursements;
import com.revature.util.annotations.Inject;

import java.util.Date;
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
        reimbursements.setStatus_id("P");
        reimbursements.setSubmitted(new Date(now));
        reimbursementDAO.save(reimbursements);
        return reimbursements;
    }
    public List<Reimbursements> getAll(){ return reimbursementDAO.getAll();}
    public List<Reimbursements> getByType(String type_id){return reimbursementDAO.getReimburseByType(type_id);}
    public List<Reimbursements> getByStatus(String status_id){return reimbursementDAO.getReimburseByStatus(status_id);}
    public List<Reimbursements> getDetailsByRemID(String reimb_id){return reimbursementDAO.getDetailsByReimburseID(reimb_id);}
    public List<Reimbursements> getPendingByUser(String author_id){return reimbursementDAO.getPendingByUser(author_id);}
}
