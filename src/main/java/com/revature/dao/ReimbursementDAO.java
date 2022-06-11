package com.revature.dao;

import com.revature.models.Reimbursements;
import com.revature.util.database.ConnectionFactory;
import com.revature.util.exceptions.InvalidSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements ICrudDAO<Reimbursements> {

    @Override
    public void save(Reimbursements obj) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("INSERT INTO ers_reimbursements VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,obj.getReimb_id());
            ps.setDouble(2, obj.getAmount());
            ps.setDate(3, new java.sql.Date(obj.getSubmitted().getTime()));
            ps.setDate(4, (Date) obj.getResolved());
            ps.setString(5,obj.getDescription());
            ps.setString(6,obj.getPayment_id());
            ps.setString(7,obj.getAuthor_id());
            ps.setString(8,obj.getResolver_id());
            ps.setString(9,obj.getStatus_id());
            ps.setString(10,obj.getType_id());
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            //throw new InvalidSQLException("An error occurred when trying to save to database");
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
                        rs.getString("description"),
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
                        rs.getString("description"),
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
    //filter ALL reimburse by status///
    public List<Reimbursements> getReimburseByStatus(String status_id){
        List<Reimbursements> reimburse = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE status_id = ?");
            ps.setString(1,status_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                reimburse.add(new Reimbursements(rs.getString("reimb_id"), rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getTimestamp("resolved"),
                        rs.getString("description"),
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


    /// view reimburse by author ID & status
    public List<Reimbursements> getPendingByUser(String author_id){
        List<Reimbursements> reimburse = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT amount, submitted, description, status_id, type_id FROM ers_reimbursements WHERE author_id = ? AND status_id = ?");
            ps.setString(1,author_id);
            ps.setString(2,"P");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                reimburse.add(new Reimbursements( rs.getDouble("amount"),
                        rs.getTimestamp("submitted"), rs.getString("description"), rs.getString("status_id"),
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
                        rs.getString("description"),
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
