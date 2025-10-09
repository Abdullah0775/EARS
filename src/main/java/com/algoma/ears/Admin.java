package com.algoma.ears;
import com.algoma.ears.util.DataTransferObject;

public class Admin implements DataTransferObject{

    private long id;
    private String userId;
    private String password;
    private String name;


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //post jobs  
    public void postJobs(String jobTitle, String jobDescription){
        Jobs newJob = new Jobs();
        newJob.setTitle(jobTitle);
        newJob.setDescription(jobDescription);
        //JobsDao call to entry in the database
    }
    //get reviews of faculties on applications
    // public Reviews getReveiw(long appId){
    //     //ReviewsDao call to get database 
    //     return new Reviews();
    // }
    // changes the status of applicatoins 
    public void updateAppStatus(Status newSt,long appId){
        //appliactionDAO findID(appId)
        //update status - 
    }

}