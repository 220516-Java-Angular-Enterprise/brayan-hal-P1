package com.revature.dtos.requests;

import com.revature.models.Reimbursements;

public class NewReimburseRequest {

    private String author_id;
    private String type_id;
    private double amount;
    private String description;



    public NewReimburseRequest(){
        super();
    }

    public NewReimburseRequest(String author_id,String type_id, double amount, String description) {
        this.author_id = author_id;
        this.type_id = type_id;
        this.amount = amount;
        this.description = description;
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "NewReimburseRequest{" +
                "author_id='" + author_id + '\'' +
                ", type_id='" + type_id + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                '}';
    }

    public Reimbursements extractReimburse(){
        return new Reimbursements(amount,description,type_id, author_id);
    }


}
