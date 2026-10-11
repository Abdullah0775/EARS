package com.algoma.ears;

import java.sql.Connection;
import java.util.List;

import com.algoma.ears.dao.AppDAO;
import com.algoma.ears.dao.FacultyDAO;
import com.algoma.ears.dao.JobsDAO;
import com.algoma.ears.dao.ReviewsDAO;

public abstract class Service {
    protected final JobsDAO jdao;
    protected final AppDAO appdao;
    protected final ReviewsDAO revdao;
    protected final FacultyDAO facdao;


    public Service(Connection con){
        this.jdao = new JobsDAO(con);
        this.appdao = new AppDAO(con);
        this.revdao = new ReviewsDAO(con);
        this.facdao = new FacultyDAO(con);
    }  
    //Read- open//
    //Jobs
    public List<Jobs> getAllJobs(){
        return jdao.findAll();
    } 
    public List<Jobs>getJobByTitle(String title){
        return jdao.findByTitle(title);
    }
    public Jobs getJobsById(long id){
        return jdao.findById(id);
    }
    // applications 
    public List<Application> getAppByJobid(long jobId){
        return appdao.findByJobId(jobId);
    } 
    public List<Application> getAllApplications(){
        return appdao.findAll();
    }
    //Reviews 
    public List<Reviews> getAppReviews(long appId){
        return revdao.findByAppId(appId);
    } 

    public abstract Admin updateProfile(Admin admin);
    public abstract Faculty updateProfile(Faculty faculty);
}
