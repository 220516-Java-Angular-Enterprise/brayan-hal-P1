package com.revature.dao;

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

    public void changeStatus(Reimbursements obj){
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps = con.prepareStatement("update ers_reimbursements")
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Reimbursements> getAllPending(){
        List<Reimbursements> pending = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select * from ers_reimbursements as er inner join  \n" +
                    "ers_reimbursement_statuses as es on er.status_id = es.status_id where status = 'PENDING'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //pending.add(new Reimbursements());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return pending;
    }

    public List<Reimbursements> getAllApproved(){
        List<Reimbursements> approved = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select * from ers_reimbursements as er inner join  \n" +
                    "ers_reimbursement_statuses as es on er.status_id = es.status_id where status = 'APPROVED'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //approved.add(new Reimbursements());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return approved;
    }

    public List<Reimbursements> getAllDeclined(){
        List<Reimbursements> declined = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select * from ers_reimbursements as er inner join  \n" +
                    "ers_reimbursement_statuses as es on er.status_id = es.status_id where status = 'DECLINED'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //declined.add(new Reimbursements());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return declined;
    }

    public List<Reimbursements> getAllLodging(){
        List<Reimbursements> lodging = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select * from ers_reimbursements as er inner join  \n" +
                    "ers_reimbursement_types as et on er.type_id = et.type_id where type = 'LODGING'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //lodging.add(new Reimbursements());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return lodging;
    }

    public List<Reimbursements> getAllTravel(){
        List<Reimbursements> travel = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select * from ers_reimbursements as er inner join  \n" +
                    "ers_reimbursement_types as et on er.type_id = et.type_id where type = 'TRAVEL'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //travel.add(new Reimbursements());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return travel;
    }

    public List<Reimbursements> getAllFood(){
        List<Reimbursements> food = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select * from ers_reimbursements as er inner join  \n" +
                    "ers_reimbursement_types as et on er.type_id = et.type_id where type = 'FOOD'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //food.add(new Reimbursements());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return food;
    }

    public List<Reimbursements> getAllOther(){
        List<Reimbursements> other = new ArrayList<>();
        try(Connection con = ConnectionFactory.getInstance().getConnection()){
            PreparedStatement ps =  con.prepareStatement("select * from ers_reimbursements as er inner join  \n" +
                    "ers_reimbursement_types as et on er.type_id = et.type_id where type = 'OTHER'");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                //other.add(new Reimbursements());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return other;
    }
}
