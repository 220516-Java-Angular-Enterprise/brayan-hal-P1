package com.revature.services;

import com.revature.dao.UserDAO;
import com.revature.dtos.requests.NewUserRequest;
import com.revature.models.Users;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void Login(){

    }

    public Users register(NewUserRequest request){
        return null;
    }

    public boolean isValidUsername(String username){
        return false;
    }

    public boolean isValidPassword(String password){
        return false;
    }

    public boolean isDuplicateUsername(String username){
        return false;
    }
}
