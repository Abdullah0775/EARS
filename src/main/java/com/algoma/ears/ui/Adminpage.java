package com.algoma.ears.ui;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Adminpage { 
    private final String style = "-fx-background-color: #084994ff; -fx-text-fill: white;-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px; -fx-border-width: 0px; -fx-border-color: transparent;";
    public Pane getView() {
        BorderPane root = new BorderPane(); 
        VBox sideBar = new VBox();
        Pane centerPane = new Pane();
        Button jobs = new Button(" Jobs ");//CRUD for jobs
        jobs.setStyle(style);
        jobs.setPrefSize(200, 50);
        jobs.setOnAction(e -> {
            // Load jobs management view in centerPane
            loadJobsManagement(centerPane);
        });
        Button faculty = new Button(" Faculty ");//CRUD for faculty
        faculty.setStyle(style);
        faculty.setPrefSize(200, 50);
        faculty.setOnAction(e -> {
            // Load faculty management view in centerPane
            // Placeholder for loading faculty management UI
            centerPane.getChildren().clear();
            // You can add your faculty management components here
            Button allFaculty = new Button("All Faculty");
            Button addFaculty = new Button("Add Faculty");
            Button editFaculty = new Button("Edit Faculty");
            Button deleteFaculty = new Button("Delete Faculty");
            TextField searchField = new TextField();
            searchField.setPromptText("Search Faculty...");
            Button searchButton = new Button("Search");
            HBox facultyManagement = new HBox(10, searchField, searchButton, allFaculty, addFaculty, editFaculty, deleteFaculty);
            centerPane.getChildren().addAll(facultyManagement);
        });
        Button applications = new Button(" Applications ");//CRUD for applications
        applications.setStyle(style);
        applications.setPrefSize(200, 50);
        applications.setOnAction(e -> {
           loadJobsManagement(centerPane);
        });
        Button profile = new Button(" Profile ");//CRUD for profile
        profile.setStyle(style);
        profile.setPrefSize(200, 50);
        sideBar.setPrefWidth(200);
        sideBar.setStyle("-fx-background-color: #084994ff;");
        sideBar.getChildren().addAll(faculty, applications, jobs, profile);
        root.setLeft(sideBar);
        root.setCenter(centerPane);
        return root;
    }
    private void loadJobsManagement(Pane centerPane) {
        // Placeholder for loading jobs management UI
        centerPane.getChildren().clear();
        // You can add your jobs management components here
        Button allJobs = new Button("All Jobs");
        Button addJob = new Button("Add Job");
        Button editJob = new Button("Edit Job");
        Button deleteJob = new Button("Delete Job");
        TextField searchField = new TextField();
        searchField.setPromptText("Search Jobs...");
        Button searchButton = new Button("Search");
        HBox jobsManagement = new HBox(10,searchField,searchButton, allJobs, addJob, editJob, deleteJob);
        //HBox searchBox = new HBox(10, searchField, searchButton); 
        centerPane.getChildren().addAll(jobsManagement);
        allJobs.setOnAction(e -> { 
            //Should I create a new card holder for this?
            //JobsPage jobsPage = new JobsPage(List<Jobs> jobs); // Assume 'jobs' is a List<Jobs> available in this context);
            //centerPane.getChildren().add(jobsPage.getView());
        });
    }
}
