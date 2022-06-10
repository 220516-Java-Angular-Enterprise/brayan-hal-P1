package com.revature.services;

import com.revature.dao.AdminDAO;
import com.revature.dtos.requests.ActiveRequest;
import com.revature.dtos.requests.ChangePassRequest;
import javafx.scene.control.TextFormatter;

public class AdminServices {
    private final AdminDAO adminDAO;

    public AdminServices(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void changeStatus(ActiveRequest activeRequest){
        adminDAO.changeActiveStatus(activeRequest.getUsername(),activeRequest.isActive());
    }

    public void changePassword(ChangePassRequest changePassRequest){
        adminDAO.changePassword(changePassRequest.getUsername(),changePassRequest.getPassword());
    }
}
