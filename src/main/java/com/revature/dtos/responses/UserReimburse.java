package com.revature.dtos.responses;

import java.sql.Date;

public class UserReimburse {
    private double amount;
    private Date sumbitted;
    private String description;
    private String status;
    private String type;

    public UserReimburse(){}

    public UserReimburse(double amount, Date sumbitted, String description, String status, String type) {
        this.amount = amount;
        this.sumbitted = sumbitted;
        this.description = description;
        this.status = status;
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getSumbitted() {
        return sumbitted;
    }

    public void setSumbitted(Date sumbitted) {
        this.sumbitted = sumbitted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserPendingReimburse{" +
                "amount=" + amount +
                ", sumbitted=" + sumbitted +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
