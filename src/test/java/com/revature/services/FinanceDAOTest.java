package com.revature.services;

import com.revature.dao.FinanceDAO;
import com.revature.dtos.responses.ReimbursementStatus;
import com.revature.dtos.responses.ReimbursementType;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FinanceDAOTest {
    private FinanceDAO financeDAOMock;
    private FinanceService financeService;
    ReimbursementType reimbursementTypeMock = new ReimbursementType();
    ReimbursementStatus reimbursementStatusMock = new ReimbursementStatus();

    @Before
    public void setup(){
        financeDAOMock = mock(FinanceDAO.class);
        financeService = new FinanceService(financeDAOMock);
    }

    @Test
    public void testFindByTypeLodging(){
        List<ReimbursementType> reimbursementTypes = Arrays.asList(reimbursementTypeMock);
        when(financeDAOMock.getAllLodging()).thenReturn(reimbursementTypes);
        Assert.assertEquals(financeDAOMock.getAllLodging(), reimbursementTypes);

    }
    @Test
    public void testFindByTypeFood(){
        List<ReimbursementType> reimbursementTypes = Arrays.asList(reimbursementTypeMock);
        when(financeDAOMock.getAllFood()).thenReturn(reimbursementTypes);
        Assert.assertEquals(financeDAOMock.getAllFood(), reimbursementTypes);

    }
    @Test
    public void testFindByTypeTravel(){
        List<ReimbursementType> reimbursementTypes = Arrays.asList(reimbursementTypeMock);
        when(financeDAOMock.getAllTravel()).thenReturn(reimbursementTypes);
        Assert.assertEquals(financeDAOMock.getAllTravel(), reimbursementTypes);

    }
    @Test
    public void testFindByStatusApproved(){
        List<ReimbursementStatus> reimbursementStatuses= Arrays.asList(reimbursementStatusMock);
        when(financeDAOMock.getAllApproved()).thenReturn(reimbursementStatuses);
        Assert.assertEquals(financeDAOMock.getAllApproved(), reimbursementStatuses);

    }
    @Test
    public void testFindByStatusDeclined(){
        List<ReimbursementStatus> reimbursementStatuses= Arrays.asList(reimbursementStatusMock);
        when(financeDAOMock.getAllDeclined()).thenReturn(reimbursementStatuses);
        Assert.assertEquals(financeDAOMock.getAllDeclined(), reimbursementStatuses);

    }

}
