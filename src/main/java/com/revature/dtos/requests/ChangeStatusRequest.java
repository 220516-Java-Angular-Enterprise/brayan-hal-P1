package com.revature.dtos.requests;

public class ChangeStatusRequest {
    private String reimb_id;
    private String status;

    public ChangeStatusRequest() {
    }

    public ChangeStatusRequest(String reimb_id, String status) {
        this.reimb_id = reimb_id;
        this.status = status;
    }

    public String getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(String reimb_id) {
        this.reimb_id = reimb_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
