package com.algoma.ears;

import java.sql.Connection;

import com.algoma.ears.dao.AdminDAO;
import com.algoma.ears.dao.FacultyDAO;

public class Authentication {
    private final Connection connection;

    public Authentication(Connection connection){
        this.connection = connection;
    } 
    public boolean adminLogIn(String userId, String password){
        AdminDAO ad = new AdminDAO(this.connection);
        Admin a = ad.findByUser(userId);
        return password.equals(a.getPassword());
    }

    public boolean facultyLogIn( String userId, String password){
        FacultyDAO fd = new FacultyDAO(this.connection);
        Faculty f = fd.findByUser(userId);
        return f.getPassword().equals(password);
    }
}
