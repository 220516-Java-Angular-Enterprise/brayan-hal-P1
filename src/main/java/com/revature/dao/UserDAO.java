package com.revature.dao;

import com.revature.models.Users;
import com.revature.util.database.ConnectionFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements ICrudDAO<Users>{


    @Override
    public void save(Users obj) {
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
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
        List<Users> users = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("select * from ers_users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                users.add(new Users(rs.getString("user_id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),
                        rs.getString("given_name"),rs.getString("surname")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    public List<String> getAllUsernames(){
        List<String> usernames = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
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

    public List<String> getAllEmails(){
        List<String> emails = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("select email from ers_users");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                emails.add(rs.getString("email"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return emails;
    }

    public boolean login(Users user){
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("select * from ers_user where username = ? and password = ?");
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

    public Users getUsernameAndPassword(String username, String password){
        Users user = null;
        try (Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("select user_id, username, password, email, given_name, surname, role from ers_users as u \n" +
                    "inner join ers_user_roles as r on u.role_id = r.role_id  WHERE username = ? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                user= new Users(rs.getString("user_id"),rs.getString("username"),rs.getString("password"),rs.getString("email"),
                        rs.getString("given_name"),rs.getString("surname"), rs.getString("role"));
            }




        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return user;
    }

}
