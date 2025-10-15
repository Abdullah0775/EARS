package com.algoma.ears.ui;

import java.util.List;

import com.algoma.ears.Jobs;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;

public class Jobspage  {
    private List<Jobs> jobs;

    public Jobspage( List<Jobs> jobs){
        this.jobs = jobs; 
    }
    public Pane getView() {
        Pane root = new Pane();
        root.setPrefSize(1000, 600);

        // Pane header = new Pane();
        // header.setPrefSize(1000, 50);
        // header.setStyle("-fx-background-color: #007bff;");
        // Label headerLabel = new Label("Job Listings");
        // headerLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        // headerLabel.setLayoutX(20);
        // headerLabel.setLayoutY(15);
        // Button loginButton = new Button("Login");
        // loginButton.setLayoutX(900);
        // loginButton.setLayoutY(10);
        // loginButton.setStyle("-fx-background-color: white; -fx-text-fill: #007bff; -fx-font-weight: bold;");
        // loginButton.setOnAction(e -> {
        //     // Handle login action
        // });
        // header.getChildren().addAll(headerLabel, loginButton);
        // //root.getChildren().add(header);

        // Create a ScrollPane to hold the job listings
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefSize(1000, 550);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(0);
        scrollPane.setFitToWidth(true);

        // Create a VBox to hold the JobCards
        javafx.scene.layout.VBox jobList = new javafx.scene.layout.VBox(10);
        jobList.setPadding(new javafx.geometry.Insets(10));
        jobList.setPrefWidth(980); // Slightly less than ScrollPane width for padding

        // Add JobCards to the VBox
        for (Jobs job : jobs) {
            JobCard jobCard = new JobCard(job);
            jobList.getChildren().add(jobCard);
        }

        scrollPane.setContent(jobList);
        root.getChildren().addAll(scrollPane);

        return root;
    }
    public void setJobs(List<Jobs> jobs) {
        this.jobs = jobs;
    }    
}
