package com.revature.services;

import com.revature.dao.FinanceDAO;
import com.revature.util.exceptions.ResourceConflictException;
import junit.framework.TestCase;
import org.junit.Assert;

public class FinanceServiceTest extends TestCase {
    FinanceService financeService = new FinanceService(new FinanceDAO());

    public void testWillThrowIfStatusEmpty(){
        String status = "";
        Assert.assertThrows(ResourceConflictException.class, () -> financeService.getByStatus(status));
    }
    public void testWillThrowIfTypeEmpty(){
        String type = "";
        Assert.assertThrows(ResourceConflictException.class, () -> financeService.getByType(type));
    }



}
