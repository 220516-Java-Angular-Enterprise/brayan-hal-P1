package com.revature.models;

public class Users {
    private String user_id;
    private String username;
    private String password;
    private String email;
    private String given_name;
    private String surname;
    private boolean is_active;
    private String role;

    public Users(){
        super();
    }

    public Users(String user_id, String username, String password, String email, String given_name, String surname) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.given_name = given_name;
        this.surname = surname;
    }

    public Users(String username, String password, String email, String given_name, String surname) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.given_name =  given_name;
        this.surname = surname;
    }

    public Users(String user_id, String username, String password, String email, String given_name, String surname, boolean isActive,String role) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.given_name = given_name;
        this.surname = surname;
        this.role = role;
        this.is_active =isActive;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGiven_name() {
        return given_name;
    }

    public void setGiven_name(String given_name) {
        this.given_name = given_name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean getIsActive() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getRole() {
        return role;
    }

    public void setRole_id(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Users{" +
                "user_id='" + user_id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", given_name='" + given_name + '\'' +
                ", surname='" + surname + '\'' +
                ", is_active=" + is_active +
                ", role_id='" + role + '\'' +
                '}';
    }
}
