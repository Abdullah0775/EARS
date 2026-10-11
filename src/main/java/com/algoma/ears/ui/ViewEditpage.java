package com.algoma.ears.ui;

import java.util.List;

import com.algoma.ears.Admin;
import com.algoma.ears.AdminService;
import com.algoma.ears.Application;
import com.algoma.ears.Faculty;
import com.algoma.ears.FacultyService;
import com.algoma.ears.Jobs;
import com.algoma.ears.Reviews;
import com.algoma.ears.Service;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ViewEditpage { 
    VBox container;
    Service service;
    public ViewEditpage(Object item,Service serv){ 
        if(serv instanceof AdminService){
            this.service = (AdminService) serv;
        } else if(serv instanceof FacultyService){
            this.service = (FacultyService) serv;
        } else{
            throw new IllegalArgumentException("Service must be either AdminService or FacultyService");
        }
        container = new VBox(10);
        if(item instanceof Admin){
            Admin admin1 = (Admin) item;
            Label ID = new Label("ID: " + admin1.getId());
            TextField nameField = new TextField(admin1.getName());
            TextField userId = new TextField(admin1.getUserId());
            TextField pass = new TextField(admin1.getPassword());
            Label nameLbl =new Label("Name: ");
            Label userLbl =new Label("User ID: ");
            Label passLbl =new Label("Password: ");
            
            Button updateBtn= new Button("Update");
            updateBtn.setOnAction(e->{
                admin1.setName(nameField.getText());
                admin1.setUserId(userId.getText());
                admin1.setPassword(pass.getText());
                service.updateProfile(admin1);
            });;
            container.getChildren().addAll(ID,nameLbl,nameField,userLbl,userId,passLbl,pass,updateBtn);
        }
        if(item instanceof Faculty){
            Faculty fac = (Faculty) item;
            Label ID = new Label("ID: " + fac.getId());
            TextField nameField = new TextField(fac.getName());
            TextField subjectField = new TextField(fac.getSubject());
            Label nameLbl =new Label("Name: ");
            Label subjectLbl =new Label("Subject: ");
            
            Button updateBtn= new Button("Update");
            updateBtn.setOnAction(e->{
                fac.setName(nameField.getText());
                fac.setSubject(subjectField.getText());
                service.updateProfile(fac);
            });;
            container.getChildren().addAll(ID,nameLbl,nameField,subjectLbl,subjectField,updateBtn);
        }
        if(item instanceof Jobs){
            Jobs job = (Jobs) item;
            if(job.getId()== 0){
                TextField jobTitle = new TextField("Job Title");
                TextField jobDesc = new TextField("Job Description");
                Button createBtn = new Button("Create Job");
                createBtn.setOnAction(e->{
                    Jobs newJob = new Jobs();
                    newJob.setTitle(jobTitle.getText());
                    newJob.setDescription(jobDesc.getText());
                    try{
                        ((AdminService) service).newJob(newJob); 
                        Label success = new Label("Job created successfully!");
                        container.getChildren().remove(createBtn);
                        container.getChildren().add(success);
                    } catch (Exception exp) {
                        Label error = new Label("Error creating job: " + exp.getMessage());
                        container.getChildren().add(error);
                    }
                });
                container.getChildren().addAll(jobTitle,jobDesc,createBtn);
                return;
            }
            Card jc =new Card();
            jc.setTitle(job.getTitle());
            jc.setDescription(job.getDescription());
            jc.setCardId(job.getId());
            List<Application> apps = service.getAppByJobid(job.getId());
            if(apps.size() == 0){
                Label noApp = new Label("No applications for this job yet.");
                container.getChildren().addAll(jc.getCard(),noApp);
                return;
            } else{
                VBox nc = Adminpage.createCardList(service.getAppByJobid(job.getId()));
                container.getChildren().addAll(jc.getCard(),nc);
            }
        }
        if(item instanceof Application){
            Application app = (Application) item;
            Card ac = new Card();
            ac.setTitle("Application for Job: " + service.getJobsById(app.getJobId()).getTitle());
            ac.setDescription("Resume location: "+ app.getResumeFileLoc() + " Status: " + app.getStatus());
            ac.setCardId(app.getId());
            ac.addButton("Change Status").setOnAction(e->{});;

            List<Reviews> revs = service.getAppReviews(app.getId());
            if(revs.size() == 0){
                Label noRev = new Label("No reviews for this application yet.");
                container.getChildren().addAll(ac.getCard(),noRev);
                return;
            } else{
                VBox nc = Adminpage.createCardList(revs);
                container.getChildren().addAll(ac.getCard(),nc);
            }
            Adminpage.createCardList(revs);
        }
        if(item instanceof Reviews){

        }
        
    } 
    public VBox getView(){
        return container;
    }
}
