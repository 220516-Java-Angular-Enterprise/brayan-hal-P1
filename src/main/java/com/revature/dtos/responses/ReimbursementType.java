package com.revature.dtos.responses;

public class ReimbursementType {
    private String reimb_id;
    private String author;
    private String description;
    private String type;

    public ReimbursementType() {
    }

    public ReimbursementType(String reimb_id, String author, String description, String type) {
        this.reimb_id = reimb_id;
        this.author = author;
        this.description = description;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "reimb_id='" + reimb_id + '\'' +
                ", author='" + author + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
