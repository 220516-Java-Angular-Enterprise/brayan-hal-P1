package com.revature.dao;

import com.revature.dtos.responses.UserReimburse;
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
                Reimbursements reimb = new Reimbursements( rs.getDouble("amount"),
                        rs.getDate("submitted"),
                        rs.getString("description"),  rs.getString("status_id"),
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
                reimburse.add(new Reimbursements( rs.getDouble("amount"),
                        rs.getDate("submitted"),
                        rs.getString("description"),  rs.getString("status_id"),
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
                reimburse.add(new Reimbursements( rs.getDouble("amount"),
                        rs.getDate("submitted"),
                        rs.getString("description"),  rs.getString("status_id"),
                        rs.getString("type_id")
                ));
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred retrieving these forms by their status");
        }
        return reimburse;
    }


    /// view reimburse by author ID & status
    public List<UserReimburse> getPendingByUser(String author_id){
        List<UserReimburse> pendingReimburses = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT amount, submitted, description, ers.status, ert.\"type\" \n" +
                    "FROM ers_reimbursements er\n" +
                    "inner join ers_reimbursement_statuses ers on er.status_id = ers.status_id \n" +
                    "inner join ers_reimbursement_types ert on er.type_id = ert.type_id \n" +
                    "WHERE author_id = ? AND er.status_id ='tUwkJ7T8wA'\n");
            ps.setString(1,author_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                pendingReimburses.add(new UserReimburse((rs.getDouble("amount")),
                        rs.getDate("submitted"), rs.getString("description"), rs.getString("status"),
                        rs.getString("type")
                ));
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred retrieving these forms");
        }
        return pendingReimburses;
    }


    public List<UserReimburse> getNewPendingFirst(String author_id){
        List<UserReimburse> pendingReimburses2 = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT amount, submitted, description, ers.status, ert.\"type\" \n" +
                    "FROM ers_reimbursements er\n" +
                    "inner join ers_reimbursement_statuses ers on er.status_id = ers.status_id \n" +
                    "inner join ers_reimbursement_types ert on er.type_id = ert.type_id \n" +
                    "WHERE author_id = ? AND er.status_id = 'tUwkJ7T8wA'\n" +
                    "order by er.submitted desc;");
            ps.setString(1,author_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                pendingReimburses2.add(new UserReimburse((rs.getDouble("amount")),
                        rs.getDate("submitted"), rs.getString("description"), rs.getString("status"),
                        rs.getString("type")
                ));
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred retrieving these forms");
        }
        return pendingReimburses2;
    }

    ///view all details by its ID//
    public List<Reimbursements> getDetailsByReimburseID(String reimb_id){
        List<Reimbursements> reimburse = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT * FROM ers_reimbursements WHERE reimb_id = ?");
            ps.setString(1,reimb_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                reimburse.add(new Reimbursements( rs.getDouble("amount"),
                        rs.getDate("submitted"),
                        rs.getString("description"),  rs.getString("status_id"),
                        rs.getString("type_id")
                ));
            }
        }catch (SQLException e){
            throw new InvalidSQLException("An error occurred retrieving these forms");
        }
        return reimburse;
    }

    public void updatePendingReimburse(double amount, String reimb_id, String description){
        try (Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("UPDATE ers_reimbursements SET amount = ? , description = ? WHERE reimb_id = ?");
            ps.setDouble(1, amount);
            ps.setString(2,description);
            ps.setString(3,reimb_id);
            ps.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<UserReimburse> getAllByUser(String author_id){
        List<UserReimburse> allUser = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT amount, submitted, description, ers.status, ert.\"type\" \n" +
                    "FROM ers_reimbursements er\n" +
                    "inner join ers_reimbursement_statuses ers on er.status_id = ers.status_id \n" +
                    "inner join ers_reimbursement_types ert on er.type_id = ert.type_id \n" +
                    "WHERE author_id = ? \n");
            ps.setString(1,author_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                allUser.add(new UserReimburse((rs.getDouble("amount")),
                        rs.getDate("submitted"), rs.getString("description"), rs.getString("status"),
                        rs.getString("type")
                ));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return allUser;
    }
    public List<UserReimburse> getUserAllNewFirst(String author_id){
        List<UserReimburse> allUser2 = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("SELECT amount, submitted, description, ers.status, ert.\"type\" \n" +
                    "FROM ers_reimbursements er\n" +
                    "inner join ers_reimbursement_statuses ers on er.status_id = ers.status_id \n" +
                    "inner join ers_reimbursement_types ert on er.type_id = ert.type_id \n" +
                    "WHERE author_id = ?\n" +
                    "order by er.submitted desc;");
            ps.setString(1,author_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                allUser2.add(new UserReimburse((rs.getDouble("amount")),
                        rs.getDate("submitted"), rs.getString("description"), rs.getString("status"),
                        rs.getString("type")
                ));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return allUser2;
    }


}
