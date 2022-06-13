package com.revature.services;

import com.revature.dao.AdminDAO;
import com.revature.dao.ReimbursementDAO;
import com.revature.util.exceptions.ResourceConflictException;
import junit.framework.TestCase;
import org.junit.Assert;

public class ReimburseServiceRequest extends TestCase {
    ReimbursementService reimbursementService = new ReimbursementService(new ReimbursementDAO());

    public void testWillThrowIfAuthorIsNullGetPending(){
        String author_id = null;
        Assert.assertThrows(ResourceConflictException.class, () -> reimbursementService.getUserPending(author_id));
    }
    public void testWillThrowIfAuthorIsNullGetAll(){
        String author_id = null;
        Assert.assertThrows(ResourceConflictException.class, () -> reimbursementService.getUserReimburseAll(author_id));
    }
    public void testWillThrowIfAuthorIsNullGetNewest(){
        String author_id = null;
        Assert.assertThrows(ResourceConflictException.class, () -> reimbursementService.getUsersNewFirst(author_id));
    }
    public void testWillThrowIfAuthorIsNullGetPendingNewest(){
        String author_id = null;
        Assert.assertThrows(ResourceConflictException.class, () -> reimbursementService.getPendingNewFirst(author_id));
    }
    public void testWillThrowIfReimburseEmpty(){
        double amount = 12;
        String reimb_id = null;
        String description = "Lots of Honey Butter Chicken Biscuits";
        Assert.assertThrows(ResourceConflictException.class, () -> reimbursementService.updatePending(amount,reimb_id,description));

    }
    public void testWillThrowIfDescriptionEmpty(){
        double amount = 12;
        String reimb_id = "10fec87b-332e-4dc5-9fc8-8204ca5aab48";
        String description = null ;
        Assert.assertThrows(ResourceConflictException.class, () -> reimbursementService.updatePending(amount,reimb_id,description));
    }


}
