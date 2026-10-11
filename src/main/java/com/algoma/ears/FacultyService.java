package com.algoma.ears;

import java.sql.Connection;

public class FacultyService extends Service {
    // jobs, applications, reviews, self
    // private final JobsDAO jdoa;
    // private final AppDAO appdao;
    // private final ReviewsDAO revdao;
    // private final FacultyDAO facdao;
    
    public FacultyService(Connection con){
       
        super(con);
        // this.jdoa = new JobsDAO(con);
        // this.appdao = new AppDAO(con);
        // this.revdao = new ReviewsDAO(con);
        // this.facdao = new FacultyDAO(con);
    } 
    //Read operations
    // public List<Jobs> getAllJobs() throws  RuntimeException{
    //     return su.jdoa.findAll();
    // }
    // public List<Jobs> getByTitle(String title)throws  RuntimeException{
    //     return this.jdoa.findByTitle(title);
    // }
    // public List<Application> getByJobId(long id)throws  RuntimeException{
    //     return this.appdao.findByJobId(id);
    // }
    // public List<Application> getAllApplication()throws  RuntimeException{
    //     return this.appdao.findAll();
    // } 
    // public List<Reviews> getRevByAppId(long id)throws  RuntimeException{
    //     return this.revdao.findByAppId(id);
    // }
    // public List<Reviews> getRevByFacId(long id)throws  RuntimeException{
    //     return this.revdao.findByFacId(id);
    // }
    // public Faculty getFaculty( long id)throws  RuntimeException{
    //     return this.facdao.findById(id);
    // }
    //Create 
    public Reviews postRev(long appId, long facId,String comment) throws  RuntimeException{
        Reviews nr = new Reviews();
        nr.setApplicationId(appId);
        nr.setFacultyId(facId);
        nr.setReviewContent(comment);
        return this.revdao.create(nr);
    }
    public Reviews postRev( Reviews review) throws RuntimeException{
        return this.revdao.create(review);
    }

    //update 
    public Reviews updateRev(long revId, String comment)throws RuntimeException{
        Reviews rev = this.revdao.findById(revId);
        rev.setReviewContent(comment);
        return this.updateRev(rev);
    }
    public Reviews updateRev(Reviews review) throws  RuntimeException{
        return this.revdao.update(review);
    } 
    public Faculty updateProfile(Faculty faculty)throws  RuntimeException{
        return this.facdao.update(faculty);
    }
    //Delete
    public void deleteRev(long id) throws RuntimeException{
        this.revdao.delete(id);
    }
    @Override
    public Admin updateProfile(Admin admin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateProfile'");
    }
}
