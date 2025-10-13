package com.algoma.ears;

import java.sql.Connection;

import com.algoma.ears.dao.AdminDAO;
import com.algoma.ears.dao.FacultyDAO;

public class Authentication {
    private Connection connection;

    public Authentication(Connection connection){
        this.connection = connection;
    } 
    public boolean adminLogIn(String userId, String password){
        AdminDAO ad = new AdminDAO(this.connection);
        Admin a = ad.findByUser(userId);
        if(password.equals(a.getPassword()))
            return true;
        return false;
    }

    public boolean facultyLogin( String userId, String password){
        FacultyDAO fd = new FacultyDAO(this.connection);
        Faculty f = fd.findByUser(userId);
        if(f.getPassword().equals(password))
            return true;
        return false;
    }
}
