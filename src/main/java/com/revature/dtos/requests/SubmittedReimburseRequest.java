package com.revature.dtos.requests;

public class SubmittedReimburseRequest {
    private String author_id;
    private String type_id;
    private double amount;
    private String description;
    private String status_id;

    public SubmittedReimburseRequest() {super();}

    public SubmittedReimburseRequest(String author_id, String type_id, double amount, String description, String status_id) {
        this.author_id = author_id;
        this.type_id = type_id;
        this.amount = amount;
        this.description = description;
        this.status_id = status_id;
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

    public String getStatus_id() {
        return status_id;
    }

    public void setStatus_id(String status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "SubmittedReimburseRequest{" +
                "author_id='" + author_id + '\'' +
                ", type_id='" + type_id + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", status_id='" + status_id + '\'' +
                '}';
    }
}
