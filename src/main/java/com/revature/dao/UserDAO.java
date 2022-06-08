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

}
