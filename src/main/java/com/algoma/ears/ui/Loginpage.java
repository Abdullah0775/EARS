package com.algoma.ears.ui;


import com.algoma.ears.Main;
import com.algoma.ears.StageHandler;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Loginpage {
     
    public Loginpage(){
        
    }
    public Pane getView() {
        Pane root = new Pane(); 

        Label userIdLabel = new Label("User ID:");
        Label passwordLabel = new Label("Password:");

        TextField userIdField = new TextField();
        PasswordField passwordField = new PasswordField();
            userIdField.setPromptText("Enter User ID");
            passwordField.setPromptText("Enter Password");
        
        Button adminLogin = new Button("Admin Login");
        adminLogin.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;");
        
        Button facultyLogin = new Button("Faculty Login");
        facultyLogin.setStyle("-fx-background-color: #007bff; -fx-text-fill : white;");
        
        Label messageLabel = new Label();

        HBox loginButtons = new HBox();
        
        loginButtons.getChildren().addAll(adminLogin, facultyLogin);
        loginButtons.setSpacing(10);
        loginButtons.setAlignment(Pos.CENTER);
        
        VBox form = new VBox(10, userIdLabel, userIdField, passwordLabel, passwordField, loginButtons, messageLabel);
        form.setPrefSize(400, 400);
        form.setLayoutX(300);
        form.setLayoutY(100);
        
        root.getChildren().add(form);
        
        adminLogin.setOnAction(e -> {
            if(Main.adminAuthentication( userIdField.getText(), passwordField.getText())){
                Adminpage admin = new Adminpage();
                StageHandler sh = new StageHandler((Stage) ((Button) e.getSource()).getScene().getWindow());
                sh.setHeader("Administration", true, "LOG OUT", false);
                sh.setCurrentView(admin.getView());
            } else {
                messageLabel.setText("Invalid User ID or Password");
            }
        });
        
        facultyLogin.setOnAction(e -> {
            if(!Main.facultyAuthentication( userIdField.getText(), passwordField.getText())){
                messageLabel.setText("Invalid User ID or Password");
                return;
            }
            StageHandler sh = new StageHandler((Stage) ((Button) e.getSource()).getScene().getWindow());
            sh.setHeader("Faculty", true, "LOG OUT", false);
            sh.setCurrentView(new Facultypage().getView());
        });
        root.setPrefSize(1000, 600);
        return root;
    }
}
