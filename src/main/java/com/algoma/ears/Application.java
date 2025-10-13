package com.algoma.ears;
import java.io.File;
import java.io.IOException;

import com.algoma.ears.util.DataTransferObject;

public class Application implements DataTransferObject{
    
    private final static String FILE_LOC = "C:/Users/Abdullah/Documents/ears_resume_files";
    private long id;
    private long jobId;
    private String resume;
    private String status;


    public void setResume(String resume) throws IOException{
        if (this.id ==0){
            throw new IllegalStateException("No Id");
        }
        File dir = new File(FILE_LOC);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File resum = new File(resume);
        this.resume = this.jobId+""+this.id+".pdf";
        File dest = new File(FILE_LOC,this.jobId+""+this.id+".pdf");
        try{
            resum.renameTo(dest);
        }catch(Exception e){
            throw e;
        }
    }

    public String getResume(){
        if(this.resume == null && this.id !=0)
            return jobId+""+this.id+".pdf";
        return this.resume;
    }
    @Override
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public long getJobId() {
        return this.jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

}