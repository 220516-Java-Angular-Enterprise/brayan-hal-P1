package com.revature.dtos.requests;

public class ActiveRequest {
    private String username;
    private boolean isActive;

    public ActiveRequest(){
        super();
    }

    public ActiveRequest(String username, boolean isActive) {
        this.username = username;
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
