package com.revature.dtos.responses;

public class ReimbursementStatus {
    private String reimb_id;
    private String author;
    private String description;
    private String status;

    public ReimbursementStatus() {
    }

    public ReimbursementStatus(String reimb_id, String author, String description, String status) {
        this.reimb_id = reimb_id;
        this.author = author;
        this.description = description;
        this.status = status;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    @Override
    public String toString() {
        return "ReimbursementStatus{" +
                "reimb_id='" + reimb_id + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
