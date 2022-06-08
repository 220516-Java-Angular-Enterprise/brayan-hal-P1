package com.revature.services;

import com.revature.dao.UserDAO;
import com.revature.dtos.requests.NewUserRequest;
import com.revature.dtos.requests.Principle;
import com.revature.models.Users;
import com.revature.util.exceptions.InvalidRequestException;
import com.revature.util.exceptions.ResourceConflictException;

import java.security.Principal;
import java.util.UUID;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Principle Login(Users user){
        userDAO.login(user);

        return null;
    }

    public Users register(NewUserRequest request){
        Users user = new Users(request.getUsername(), request.getPassword(), request.getEmail(), request.getName(), request.getSurname());
        if(isNotDuplicateUsername(user.getUsername())){
            if(isNotDuplicateEmail(user.getEmail())){
                if(isValidUsername(user.getUsername())){
                    if(isValidPassword(user.getPassword())){
                        user.setUser_id(UUID.randomUUID().toString());
                        userDAO.save(user);
                    }else throw new InvalidRequestException("Invalid password. Minimum eight characters, at least one letter, one number and one special character.");
                }else throw new InvalidRequestException("Invalid username. Username needs to be 8-20 characters long.");
            }else throw new ResourceConflictException("Email is already taken ");
        }else throw new ResourceConflictException("Username is already taken ");
        return user;
    }

    public boolean isValidUsername(String username){
        return username.matches("^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
    }

    public boolean isValidPassword(String password){
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
    }

    public boolean isNotDuplicateUsername(String username){
        return !userDAO.getAllUsernames().contains(username);
    }

    public boolean isNotDuplicateEmail(String email){return !userDAO.getAllEmails().contains(email);}
}
