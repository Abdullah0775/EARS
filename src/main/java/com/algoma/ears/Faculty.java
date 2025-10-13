package com.algoma.ears;
import com.algoma.ears.util.DataTransferObject;

public class Faculty implements DataTransferObject{

    private long id; 
    private String userId;
    private String password;
    private String name;
    private String subject;

    @Override
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password= password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    //Creation of reviews for job applications
    public Reviews createReveiw(long revId, long appId,String revContent){
        Reviews newReview = new Reviews(); 
        String ss = this.getId()+""+appId;
        revId = Integer.parseInt(ss);
        newReview.setId(revId);
        newReview.setApplicationId(appId);
        newReview.setFacultyId(this.getId());
        newReview.setReviewContent(revContent);
        //ReviewDAO.insert(newReview);
        return newReview;
    }

}