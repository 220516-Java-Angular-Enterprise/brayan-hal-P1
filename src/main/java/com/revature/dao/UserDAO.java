package com.revature.dao;

import com.revature.models.Users;
import com.revature.util.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements ICrudDAO<Users>{
    Connection con = DatabaseConnection.getCon();

    @Override
    public void save(Users obj) {
        try{
            PreparedStatement ps = con.prepareStatement("insert into ers_users(user_id, username, password, email, given_name, surname) values(?,?,?,?,?,?)");
            ps.setString(1, obj.getUser_id());
            ps.setString(2, obj.getUsername());
            ps.setString(3, obj.getPassword());
            ps.setString(4, obj.getEmail());
            ps.setString(5, obj.getGiven_name());
            ps.setString(6, obj.getSurname());
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Users obj) {

    }

    @Override
    public void delete(Users obj) {

    }

    @Override
    public List<Users> getAll() {
        return null;
    }

    public List<String> getAllUsernames(){
        List<String> usernames = new ArrayList<>();
        try{
            PreparedStatement ps = con.prepareStatement("select username from ers_users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                usernames.add(rs.getString("username"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return usernames;
    }

    public boolean login(Users user){
        try{
            PreparedStatement ps = con.prepareStatement("select * from user where username = ? and password = ?");
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ResultSet rs = ps.executeQuery();
            if(rs.next())return true;
            else return false;
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

}
