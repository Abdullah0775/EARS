package com.algoma.ears;
import com.algoma.ears.util.*;
public class Application implements DataTransferObject{

    private long id;
    private long jobId;
    private String resumeFileLoc;
    private Status status;

    @Override
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Status getStatus(){
        return this.status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

    public long getJobId() {
        return this.jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getResumeFileLoc() {
        return this.resumeFileLoc;
    }

    public void setResumeFileLoc(String resumeFileLoc) {
        this.resumeFileLoc = resumeFileLoc;
    }
}