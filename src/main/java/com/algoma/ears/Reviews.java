package com.algoma.ears;

import com.algoma.ears.util.DataTransferObject;

public class Reviews implements DataTransferObject {

    private long id;
    private long facultyId;
    private long applicationId;
    private String reviewContent;

    @Override
    public long getId(){
        return this.id;
    }    

    public void setId(long id) {
        this.id = id;
    }

    public long getFacultyId() {
        return this.facultyId;
    }

    public void setFacultyId(long facultyId) {
        this.facultyId = facultyId;
    }

    public long getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getReviewContent() {
        return this.reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }
}