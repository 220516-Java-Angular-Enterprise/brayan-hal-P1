package com.revature.services;

import com.revature.dao.ReimbursementDAO;
import com.revature.util.annotations.Inject;

public class ReimbursementService {
    @Inject
    private final ReimbursementDAO reimbursementDAO;

    @Inject
    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }
}
