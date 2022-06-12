package com.revature.dao;

import com.revature.dtos.responses.ReimbursementStatus;
import com.revature.dtos.responses.ReimbursementType;
import com.revature.models.Reimbursements;
import com.revature.util.database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FinanceDAO implements ICrudDAO<Reimbursements>{
    @Override
    public void save(Reimbursements obj) {

    }

    @Override
    public void update(Reimbursements obj) {

    }

    @Override
    public void delete(Reimbursements obj) {

    }

    @Override
    public List<Reimbursements> getAll() {
        return null;
    }

    public void changeStatus(String status, String reimb_id){
        switch(status){
            case "APPROVED":
                status = "tUwkJ7H9wB";
                break;
            case "PENDING":
                status = "tUwkJ7H9wA";
                break;
            case "DENIED":
                status = "tUwkJ7X0wC";
                break;
        }
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("update ers_reimbursements set status_id = ? where reimb_id = ?");
            ps.setString(1, status);
            ps.setString(2, reimb_id);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<ReimbursementStatus> getAllPending(){
        List<ReimbursementStatus> pending = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select reimb_id, username, description, status  from ers_reimbursements as er \n" +
                    "inner join ers_reimbursement_statuses as es on er.status_id = es.status_id \n" +
                    "inner join ers_users as eu on er.author_id = eu.user_id where status = 'PENDING'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                pending.add(new ReimbursementStatus(rs.getString("reimb_id"),rs.getString("username"),
                        rs.getString("description"), rs.getString("status")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return pending;
    }


    public List<ReimbursementStatus> getAllApproved(){
        List<ReimbursementStatus> approved = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select reimb_id, username, description, status  from ers_reimbursements as er \n" +
                    "inner join ers_reimbursement_statuses as es on er.status_id = es.status_id \n" +
                    "inner join ers_users as eu on er.author_id = eu.user_id where status = 'APPROVED'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                approved.add(new ReimbursementStatus(rs.getString("reimb_id"),rs.getString("username"),
                                        rs.getString("description"), rs.getString("status")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return approved;
    }

    public List<ReimbursementStatus> getAllDeclined(){
        List<ReimbursementStatus> declined = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select reimb_id, username, description, status  from ers_reimbursements as er \n" +
                    "inner join ers_reimbursement_statuses as es on er.status_id = es.status_id \n" +
                    "inner join ers_users as eu on er.author_id = eu.user_id where status = 'DECLINED'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                declined.add(new ReimbursementStatus(rs.getString("reimb_id"),rs.getString("username"),
                        rs.getString("description"), rs.getString("status")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return declined;
    }

    public List<ReimbursementType> getAllLodging(){
        List<ReimbursementType> lodging = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select reimb_id, username, description, type  from ers_reimbursements as er \n" +
                    "inner join ers_reimbursement_types as et on er.type_id = et.type_id \n" +
                    "inner join ers_users as eu on er.author_id = eu.user_id where type = 'LODGING'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                lodging.add(new ReimbursementType(rs.getString("reimb_id"),rs.getString("username"),
                        rs.getString("description"), rs.getString("type")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lodging;
    }


    public List<ReimbursementType> getAllTravel(){
        List<ReimbursementType> travel = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select reimb_id, username, description, type  from ers_reimbursements as er \n" +
                    "inner join ers_reimbursement_types as et on er.type_id = et.type_id \n" +
                    "inner join ers_users as eu on er.author_id = eu.user_id where type = 'TRAVEL'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                travel.add(new ReimbursementType(rs.getString("reimb_id"),rs.getString("username"),
                        rs.getString("description"), rs.getString("type")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return travel;
    }


    public List<ReimbursementType> getAllFood(){
        List<ReimbursementType> food = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select reimb_id, username, description, type  from ers_reimbursements as er \n" +
                    "inner join ers_reimbursement_types as et on er.type_id = et.type_id \n" +
                    "inner join ers_users as eu on er.author_id = eu.user_id where type = 'FOOD'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                food.add(new ReimbursementType(rs.getString("reimb_id"),rs.getString("username"),
                        rs.getString("description"), rs.getString("type")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return food;
    }


    public List<ReimbursementType> getAllOther(){
        List<ReimbursementType> other = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select reimb_id, username, description, type  from ers_reimbursements as er \n" +
                    "inner join ers_reimbursement_types as et on er.type_id = et.type_id \n" +
                    "inner join ers_users as eu on er.author_id = eu.user_id where type = 'OTHER'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                other.add(new ReimbursementType(rs.getString("reimb_id"),rs.getString("username"),
                        rs.getString("description"), rs.getString("type")));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return other;
    }
}
