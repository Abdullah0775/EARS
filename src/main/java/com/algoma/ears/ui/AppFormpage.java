package com.algoma.ears.ui;

import com.algoma.ears.Application;
import com.algoma.ears.Jobs;
import com.algoma.ears.Main;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class AppFormpage {
    
    private long jobId;
    private String jobTitle;
    private String jobDescription;

    public AppFormpage(Jobs job) {
        this.jobId = job.getId();
        this.jobTitle = job.getTitle();
        this.jobDescription = job.getDescription();
    } 

    public Pane getView() {
        Pane root = new Pane();
        root.setPrefSize(1000, 600);
        
        Label title = new Label("Apply for Job: " + jobTitle);
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        Label desc = new Label("Job Description: " + jobDescription);
        
        Label id = new Label("Job ID: " + jobId);
        
        Label resume  = new Label("Resume:");
        
        Label msgLabel = new Label();

        TextField resumeF = new TextField();
        resumeF.setPromptText("Enter your resume link");
        Button submit = new Button("Submit Application");
        submit.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        submit.setOnAction(e -> {
            try { 
                System.out.println("Submitting application for job ID: " + this.jobId + " with resume: " + resumeF.getText());
                Application app = Main.postApplication(this.jobId, resumeF.getText());
                msgLabel.setText("Application submitted successfully!"+
                    "\nID:  "+app.getId()+" Status: "+app.getStatus());
            } catch ( RuntimeException ex) {
                msgLabel.setText("Error reading resume: " + ex.getMessage());
            }

        });
        VBox form = new VBox(10, title, desc, id, resume, resumeF, submit, msgLabel);
        form.setPrefSize(800, 400);
        form.setLayoutX(100);
        form.setLayoutY(100);
        root.getChildren().add(form);
        return root;
    }
    
}
