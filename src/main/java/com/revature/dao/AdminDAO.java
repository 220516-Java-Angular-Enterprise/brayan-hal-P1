package com.revature.dao;

import com.revature.dtos.requests.ActiveRequest;
import com.revature.models.Users;
import com.revature.util.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AdminDAO implements ICrudDAO<Users>{
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

    public void changeActiveStatus(String username, boolean isActive){
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("update ers_users set is_active = ? where username = ?");
            ps.setBoolean(1, isActive);
            ps.setString(2, username);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void changePassword(String username, String password){
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("update ers_users set password = crypt(?,password) where username = ?");
            ps.setString(1, password);
            ps.setString(2, username);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
