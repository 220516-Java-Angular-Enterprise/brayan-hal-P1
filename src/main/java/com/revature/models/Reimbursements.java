package com.revature.models;

import java.sql.Timestamp;
import java.sql.Date;

public class Reimbursements {
    private String reimb_id;
    private double amount;
    private Date submitted;
    private Date resolved;
    private String description;
    private String payment_id;
    private String author_id;
    private String resolver_id;
    private String status_id;
    private String type_id;
public Reimbursements(){ super();}
    public Reimbursements(double amount, String description, String type_id, String author_id){
        this.amount = amount;
        this.description = description;
        this.author_id = author_id;
        this.type_id = type_id;
    }

    public Reimbursements(double amount, Date submitted, String description, String author_id, String status_id, String type_id) {
        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.author_id = author_id;
        this.status_id = status_id;
        this.type_id = type_id;
    }



    public Reimbursements(double amount, java.sql.Date submitted, String description, String status_id, String type_id) {
        this.amount = amount;
        this.submitted = submitted;
        this.description = description;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Date submitted) {
        this.submitted = submitted;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getResolver_id() {
        return resolver_id;
    }

    public void setResolver_id(String resolver_id) {
        this.resolver_id = resolver_id;
    }

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "Reimbursements{" +
                "reimb_id='" + reimb_id + '\'' +
                ", amount=" + amount +
                ", submitted=" + submitted +
                ", resolved=" + resolved +
                ", description='" + description + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", author_id='" + author_id + '\'' +
                ", resolver_id='" + resolver_id + '\'' +
                ", status_id='" + status_id + '\'' +
                ", type_id='" + type_id + '\'' +
                '}';
    }
}
