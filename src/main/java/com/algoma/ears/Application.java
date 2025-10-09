package com.algoma.ears;
import com.algoma.ears.util.*;
import java.io.*;
public class Application implements DataTransferObject{
    
    private final static String FILE_LOC = "C:/Users/Abdullah/Documents/ears_resume_files";
    private long id;
    private long jobId;
    private File resume;
    private Status status;


    public void setResume(File resume) throws IOException{
        if (this.id ==0){
            throw new IllegalStateException("No Id");
        }
        File dir = new File(FILE_LOC);
        if(!dir.exists()){
            dir.mkdirs();
        } 
        File dest = new File(FILE_LOC,this.jobId+this.id+".pdf");
        try{
            resume.renameTo(dest);
            this.resume = dest;
        }catch(Exception e){
            System.err.println("Error storing file");
            throw e;
        }
    }

    public File getResume(){
        if(this.resume == null && this.id !=0)
            return new File(FILE_LOC,this.jobId+this.id+".pdf");
        return this.resume;
    }
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

}