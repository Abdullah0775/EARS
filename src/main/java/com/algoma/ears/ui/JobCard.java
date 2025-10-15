package com.algoma.ears.ui;

import com.algoma.ears.Jobs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JobCard extends HBox {

    public JobCard(Jobs job) {
       
        this.setWidth(900);
        this.setHeight(150);
        this.setSpacing(10);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(15));
        this.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-color: #f7f7f7; -fx-padding: 10;");

        Label idLabel = new Label("ID: " + job.getId());
        Label titleLabel = new Label(job.getTitle());
        Label descLabel = new Label(job.getDescription());
        
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px;");
        descLabel.setWrapText(true); 

        VBox textContent = new VBox(5, titleLabel, descLabel, idLabel);
        textContent.setPrefWidth(900); 

        Button applyButton = new Button("Apply");
        applyButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;"); 
        applyButton.setPadding(new Insets(10, 20, 10, 20));
        applyButton.setMinWidth(100);
        applyButton.setOnAction(e -> {
            AppFormpage app = new AppFormpage(job);
            StageHandler sh = new StageHandler((Stage)this.getScene().getWindow()); 
            sh.setPrevView((Pane) this.getScene().getRoot());
            sh.setHeader("Application", true, "LOG IN", true);
            sh.setCurrentView(app.getView()); 
            e.consume();
        });
        
        VBox buttonWrapper = new VBox(applyButton);
        buttonWrapper.setAlignment(Pos.BOTTOM_RIGHT);
        
        this.getChildren().addAll(textContent, buttonWrapper);
    }
}
