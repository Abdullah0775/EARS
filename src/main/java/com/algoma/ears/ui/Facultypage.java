package com.algoma.ears.ui;

import java.sql.Connection;
import java.util.List;

import com.algoma.ears.Application;
import com.algoma.ears.FacultyService;
import com.algoma.ears.Jobs;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Facultypage {
    
    private FacultyService facService;
    private long id;
    private Connection conn;

    public Facultypage(Connection conn, long id){
        this.conn = conn;
        this.id = id;
        this.facService = new FacultyService(conn);
    }
    
    // private final String style = "-fx-background-color: #084994ff; -fx-text-fill: white;-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px; -fx-border-width: 0px; -fx-border-color: transparent;";
    public Pane getView() {
        BorderPane root = new BorderPane(); 
        ScrollPane centerPane = new ScrollPane();

        NavCol sideBar = new NavCol();
        sideBar.addItem(" Jobs ").setOnAction(e->{
            List<Jobs> jobs = facService.getAllJobs();
            centerPane.setContent(CardList.createCardList(jobs));    
        });;

        sideBar.addItem(" Applications ").setOnAction(e->{
           List<Application> apps = facService.getAllApplications();
           centerPane.setContent(CardList.createCardList(apps));
        });; 

        sideBar.addItem(" Profile ").setOnAction(e->{
            System.out.println(id);
        });
        
        root.setLeft(sideBar);
        root.setCenter(centerPane);
        return root;
        
        // Button jobs = new Button(" Jobs ");//CRUD for jobs
        // jobs.setStyle(style);
        // jobs.setPrefSize(200, 50);
        // Button applications = new Button(" Applications ");//CRUD for applications
        // applications.setStyle(style);
        // applications.setPrefSize(200, 50);
        // Button profile = new Button(" Profile ");//CRUD for profile
        // profile.setStyle(style);
        // profile.setPrefSize(200, 50);
        // sideBar.setPrefWidth(200);
        // sideBar.setStyle("-fx-background-color: #084994ff;");
        // sideBar.getChildren().addAll(jobs, applications, profile);
        // root.setLeft(sideBar);
        // root.setCenter(centerPane);
        // return root;
    }

}
