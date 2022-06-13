package com.revature.services;

import com.revature.dao.UserDAO;
import com.revature.util.exceptions.InvalidRequestException;
import com.revature.util.exceptions.InvalidUserException;
import com.revature.util.exceptions.ResourceConflictException;
import junit.framework.TestCase;
import org.junit.Assert;

public class UserServiceTest extends TestCase {
    UserService userService = new UserService(new UserDAO());

    public void testWillThrowIfPasswordEmpty(){
        String password = "";
        Assert.assertThrows(InvalidRequestException.class, () -> userService.isValidPassword(password));
    }

    public void testWillThrowIfUsernameEmpty(){
        String username = "";
        Assert.assertThrows(InvalidRequestException.class, () -> userService.isValidUsername(username));
    }

    public void testWillThrowIfDuplicateUsername(){
        String username = "halcoro1";
        Assert.assertThrows(ResourceConflictException.class, () -> userService.isNotDuplicateUsername(username));
    }

    public void testWillWillThrowIfDuplicateEmail(){
        String email = "finance101@gmail.com";
        Assert.assertThrows(ResourceConflictException.class, () -> userService.isNotDuplicateEmail(email));
    }


}
