package com.revature.dtos.responses;

import com.revature.models.Users;

public class Principle {
    //unique attributes to user -- can add more later--//
    private String username;
    private String user_id;
    private String role;
    private boolean is_active;

    public Principle(){
        super();
    }

    public Principle(Users users) {
        this.username = users.getUsername();
        this.user_id = users.getUser_id();
        this.role = users.getRole();
        this.is_active = users.getIsActive();
    }

    public Principle(String user_id, String username, String role) {
        this.user_id = user_id;
        this.username = username;
        this.role = role;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
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
