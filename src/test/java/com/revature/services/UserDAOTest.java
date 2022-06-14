package com.revature.services;

import com.revature.dao.UserDAO;
import com.revature.dtos.requests.LoginRequest;
import com.revature.dtos.requests.NewUserRequest;
import com.revature.models.Users;
import com.revature.util.exceptions.ResourceConflictException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDAOTest {
    private UserDAO userDAOMock;
    private UserService userService;

    LoginRequest loginRequestMock = new LoginRequest();
    NewUserRequest newUserRequestMock = new NewUserRequest();
    Users usersMock = new Users();


    @Before
    public void setup(){
        userDAOMock = mock(UserDAO.class);
        userService = new UserService(userDAOMock);
    }

    @Test
    public void testGetUserAndPassword(){
        when(userDAOMock.getUsernameAndPassword(loginRequestMock.getUsername(),loginRequestMock.getPassword())).thenReturn(usersMock);
        Assert.assertEquals(userDAOMock.getUsernameAndPassword(loginRequestMock.getUsername(),loginRequestMock.getPassword()),usersMock);
    }
    @Test
    public void testLoginUser(){
        when(userDAOMock.login(usersMock)).thenReturn(true);
        assertTrue(userDAOMock.login(usersMock));
    }
    @Test
    public void testNotDuplicateEmail(){
        List<String> emails = new ArrayList<>();
        emails.add("x");
        when(userDAOMock.getAllEmails()).thenReturn(emails);

        assertTrue(userService.isNotDuplicateEmail("y"));
    }
    @Test
    public void testIsDuplicateEmail(){
        List<String> emails = new ArrayList<>();
        emails.add("x");
        when(userDAOMock.getAllEmails()).thenReturn(emails);

        assertThrows(ResourceConflictException.class, () ->userService.isNotDuplicateEmail("x"));
    }
}
