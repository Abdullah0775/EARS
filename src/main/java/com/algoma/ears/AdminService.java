package com.algoma.ears;
import com.algoma.ears.dao.*;
import java.sql.Connection;
import java.util.List;

public class AdminService {
    private Connection conn; 

    public AdminService(Connection conn){
        this.conn = conn;
    } 

    public List<Application> getAllApplications(){
        AppDAO appDao = new AppDAO(this.conn);
        return appDao.findAll();
    }
    public Application updateApplicationStatus(long appId, String newStatus){
        AppDAO appDao = new AppDAO(this.conn);
        Application app = appDao.findById(appId);
        app.setStatus(newStatus);
        return appDao.update(app);
    } 

    public List<Reviews> getAllReviews(){
        ReviewsDAO revDao = new ReviewsDAO(this.conn);
        return revDao.findAll();
    }

    
}
