package com.revature.dtos.requests;

public class UpdatePendingRequest {
    private double amount;
    private String reimb_id;
    private String description;
    private String status_id;

    public UpdatePendingRequest(){super();}

    public UpdatePendingRequest(double amount, String reimb_id, String description) {
        this.amount = amount;
        this.reimb_id = reimb_id;
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
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
        return "UpdatePendingRequest{" +
                "amount=" + amount +
                ", reimb_id='" + reimb_id + '\'' +
                ", description='" + description + '\'' +
                ", status_id='" + status_id + '\'' +
                '}';
    }
}
