package com.algoma.ears.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Facultypage {
        private final String style = "-fx-background-color: #084994ff; -fx-text-fill: white;-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px; -fx-border-width: 0px; -fx-border-color: transparent;";
    public Pane getView() {
        BorderPane root = new BorderPane(); 
        VBox sideBar = new VBox();
        Pane centerPane = new Pane();
        Button jobs = new Button(" Jobs ");//CRUD for jobs
        jobs.setStyle(style);
        jobs.setPrefSize(200, 50);
        Button applications = new Button(" Applications ");//CRUD for applications
        applications.setStyle(style);
        applications.setPrefSize(200, 50);
        Button profile = new Button(" Profile ");//CRUD for profile
        profile.setStyle(style);
        profile.setPrefSize(200, 50);
        sideBar.setPrefWidth(200);
        sideBar.setStyle("-fx-background-color: #084994ff;");
        sideBar.getChildren().addAll(jobs, applications, profile);
        root.setLeft(sideBar);
        root.setCenter(centerPane);
        return root;
    }

}
