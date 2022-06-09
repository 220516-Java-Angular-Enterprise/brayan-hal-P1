package com.revature.services;

import com.revature.dao.ReimbursementDAO;
import com.revature.models.Reimbursements;
import com.revature.util.annotations.Inject;

import java.util.List;

public class ReimbursementService {
    @Inject
    private final ReimbursementDAO reimbursementDAO;

    @Inject
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public void saveReimbursement(Reimbursements reimbursements){ reimbursementDAO.save(reimbursements);}
    public List<Reimbursements> getAll(){ return reimbursementDAO.getAll();}
    public List<Reimbursements> getByType(String type_id){return reimbursementDAO.getReimburseByType(type_id);}
    public List<Reimbursements> getByStatus(String status_id){return reimbursementDAO.getReimburseByStatus(status_id);}
    public List<Reimbursements> getDetailsByRemID(String reimb_id){return reimbursementDAO.getDetailsByReimburseID(reimb_id);}
    public List<Reimbursements> getRemByUser(String author_id){return reimbursementDAO.getReimburseByUser(author_id);}
}
