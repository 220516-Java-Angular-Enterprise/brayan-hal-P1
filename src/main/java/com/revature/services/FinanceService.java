package com.revature.services;

import com.revature.dao.FinanceDAO;
import com.revature.util.annotations.Inject;

public class FinanceService {
    @Inject
    private final FinanceDAO financeDAO;

    @Inject
    public FinanceService(FinanceDAO financeDAO) {
        this.financeDAO = financeDAO;
    }


}
