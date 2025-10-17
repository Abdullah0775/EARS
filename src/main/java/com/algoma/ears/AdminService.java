package com.algoma.ears;
import java.sql.Connection;
import java.util.List;

import com.algoma.ears.dao.AdminDAO;
import com.algoma.ears.dao.AppDAO;
import com.algoma.ears.dao.FacultyDAO;
import com.algoma.ears.dao.JobsDAO;
import com.algoma.ears.dao.ReviewsDAO;

public class AdminService{
    private JobsDAO jdao;
    private AppDAO appDao;
    private ReviewsDAO revDao;
    private FacultyDAO facDao;
    private AdminDAO admnDao;
    //Admin can
    
    //get all jobs and also by title 
    // get applications on jobs
    // get reviews on applications
    // get all faculty, and by name/ subject
    //get self info from admin as profile

    //create jobs
    // creates new faculty ... 
    
    //updates applicatoins
    //updates jobs 
    //updates admin self

    // delete jobs leading 
    //deletion of related applis
    //leading to deletion of related reviews
    //deletion of faculty


    
    public AdminService(Connection conn){
        this.jdao = new JobsDAO(conn);
        this.appDao = new AppDAO(conn);
        this.revDao = new ReviewsDAO(conn);
        this.facDao = new FacultyDAO(conn);
        this.admnDao = new AdminDAO(conn);
    } 

    //Read- open//
    //Jobs
    public List<Jobs> getAllJobs(){
        return this.jdao.findAll();
    } 
    public List<Jobs>getJobByTitle(String title){
        return this.jdao.findByTitle(title);
    }
    // applicatoins 
    public List<Application> getAppByJobid(long jobId){
        return this.appDao.findByJobId(jobId);
    } 
     public List<Application> getAllApplications(){
        return this.appDao.findAll();
    }
    //Reviews 
    public List<Reviews> getAppReviews(long appId){
        return this.revDao.findByAppId(appId);
    }
    //Faculty
    public List<Faculty> getAllFac(){
        return this.facDao.findAll();
    }
    public List<Faculty> getFacByName(String facultyName){
        return this.facDao.findByName(facultyName);
    }
    public List<Faculty> getFacByDepart(String department){
        return this.facDao.findBySub(department);
    }
    //Admin
    public Admin getProfile(long admnId){
        return this.admnDao.findById(admnId);
    }
    //Read - close//

    //Create - open//
    //jobs
    public Jobs newJob(Jobs nj){
        return this.jdao.create(nj);
    }
    //faculty
    public Faculty newFac(Faculty fac){
        return this.facDao.create(fac);
    }
    //Create - close//

    //Update - open//
    //Jobs
    public Jobs updateJob(Jobs updatedJob){
        return this.jdao.update(updatedJob);
    }
    //applcation
    public Application updateApplicationStatus(long appId, String newStatus){
        Application app = appDao.findById(appId);
        app.setStatus(newStatus);
        return appDao.update(app);
    }
    public Application updateApplicationStatus(Application application){
        return appDao.update(application);
    }
    //Admin Self 
    public Admin updateProfile(Admin admin){
        return this.admnDao.update(admin);
    }
    //Update - close//

    //Delete - open//
    public void deleteJob(Jobs job){
        //finds the application related to the job
        List<Application> a = this.appDao.findByJobId(job.getId());
        for(Application e : a){
            //for each job find the Reviews on it 
            List<Reviews> r =this.revDao.findByAppId(e.getId());
            //Deleting each review
            for(Reviews h : r)
                this.revDao.delete(h.getId());
            //Deleting each application
            this.appDao.delete(e.getId());
        }
        //then finally atlast deleting the job, Phew too much work
        this.jdao.delete(job.getId());
    }
    //Delete  - Close//
    
}
