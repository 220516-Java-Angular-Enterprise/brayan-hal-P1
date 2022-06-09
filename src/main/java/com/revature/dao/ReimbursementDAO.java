package com.revature.dao;

import com.revature.models.Reimbursements;
import com.revature.util.database.ConnectionFactory;
import com.revature.util.exceptions.InvalidSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements ICrudDAO<Reimbursements> {

    @Override
    public void save(Reimbursements obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_reimbursements VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,obj.getReimb_id());
            ps.setDouble(2, obj.getAmount());
            ps.setTimestamp(3, obj.getSubmitted());
            ps.setTimestamp(4, obj.getResolved());
            ps.setString(5,obj.getDescription());
            ps.setByte(6, obj.getReceipt());
            ps.setString(7,obj.getPayment_id());
            ps.setString(8,obj.getAuthor_id());
            ps.setString(9,obj.getResolver_id());
            ps.setString(10,obj.getStatus_id());
            ps.setString(11,obj.getType_id());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred when trying to save to database");
        }

    }

    @Override
    public void update(Reimbursements obj) {

    }

    @Override
    public void delete(Reimbursements obj) {

    }

    @Override
    public List<Reimbursements> getAll() {
        List<Reimbursements> reimburse = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT* FROM ers_reimbursements");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Reimbursements reimb = new Reimbursements(
                        rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"),
                        rs.getString("description"), rs.getByte("receipt"),
                        rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"),
                        rs.getString("type_id")
                );
                reimburse.add(reimb);
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred while trying to get all Reimbursements");
        }
        return reimburse;
    }
        //filter reimburse by type ///
    public List<Reimbursements> getReimburseByType(String type_id){
        List<Reimbursements> reimburse = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE type_id = ?");
            ps.setString(1,type_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                reimburse.add(new Reimbursements(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"),
                        rs.getString("description"), rs.getByte("receipt"),
                        rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"),
                        rs.getString("type_id")
                ));
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred retrieving these forms by their type");
        }
    return reimburse;
    }
    //filter reimburse by status///
    public List<Reimbursements> getReimburseByStatus(String status_id){
        List<Reimbursements> reimburse = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE status_id = ?");
            ps.setString(1,status_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                reimburse.add(new Reimbursements(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"),
                        rs.getString("description"), rs.getByte("receipt"),
                        rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"),
                        rs.getString("type_id")
                ));
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred retrieving these forms by their status");
        }
        return reimburse;
    }


    /// view reimburse by author ID or username???, lists out not null details
    public List<Reimbursements> getReimburseByUser(String author_id){
        List<Reimbursements> reimburse = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE author_id = ?");
            ps.setString(1,author_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                reimburse.add(new Reimbursements( rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getString("description"),
                        rs.getString("author_id"), rs.getString("status_id"),
                        rs.getString("type_id")
                ));
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred retrieving these forms");
        }
        return reimburse;
    }
    ///view all details by its ID//
    public List<Reimbursements> getDetailsByReimburseID(String reimb_id){
        List<Reimbursements> reimburse = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE reimb_id = ?");
            ps.setString(1,reimb_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                reimburse.add(new Reimbursements(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"),
                        rs.getString("description"), rs.getByte("receipt"),
                        rs.getString("payment_id"), rs.getString("author_id"),
                        rs.getString("resolver_id"), rs.getString("status_id"),
                        rs.getString("type_id")
                ));
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred retrieving these forms");
        }
        return reimburse;
    }
}
