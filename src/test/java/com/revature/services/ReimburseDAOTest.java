package com.revature.services;

import com.revature.dao.ReimbursementDAO;
import com.revature.dtos.responses.UserReimburse;
import com.revature.models.Reimbursements;
import com.revature.util.exceptions.ResourceConflictException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReimburseDAOTest {
    private ReimbursementDAO reimbursementDAOMock;
    private ReimbursementService reimbursementService;
    UserReimburse userReimburseMock = new UserReimburse();
    Reimbursements reimbursementsMock = new Reimbursements();


    @Before
    public void setup(){
        reimbursementDAOMock = mock(ReimbursementDAO.class);
        reimbursementService = new ReimbursementService(reimbursementDAOMock);
    }
    @Test
    public void testFindAllPending(){
        List<UserReimburse> userReimburses = Arrays.asList(userReimburseMock);
        when(reimbursementDAOMock.getPendingByUser("x")).thenReturn(userReimburses);
        assertEquals(reimbursementDAOMock.getPendingByUser("x"),userReimburses);
    }
    @Test
    public void testFindAllUsers(){
        List<UserReimburse> userReimburses = Arrays.asList(userReimburseMock);
        when(reimbursementDAOMock.getAllByUser("q")).thenReturn(userReimburses);
        assertEquals(reimbursementDAOMock.getAllByUser("q"),userReimburses);
    }
    @Test
    public void testFindDetailsByRemID(){
        List<Reimbursements> userReimburses = Arrays.asList(reimbursementsMock);
        when(reimbursementDAOMock.getDetailsByReimburseID("x")).thenReturn(userReimburses);
        assertEquals(reimbursementDAOMock.getDetailsByReimburseID("x"),userReimburses);
    }
}
