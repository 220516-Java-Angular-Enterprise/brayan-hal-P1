package com.revature.dtos.responses;

import com.revature.models.Users;

public class Principle {
    //unique attributes to user -- can add more later--//
    private String username;
    private String user_id;
    private String role;

    public Principle(){
        super();
    }

    public Principle(Users users) {
        this.username = users.getUsername();
        this.user_id = users.getUser_id();
        this.role = users.getRole();
    }

    public Principle(String username, String user_id, String role) {
        this.username = username;
        this.user_id = user_id;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Principle{" +
                "username='" + username + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
