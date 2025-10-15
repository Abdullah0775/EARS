package com.algoma.ears.ui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// this class takes the Panes from the ui package and sets them in the stage
//It does not handle navigation between views

public class StageHandler {
    private Pane currentView; 
    private Scene scene;
    private Stage stage; 
    private Pane prevView;
    private String label;
    private String buttonText; 
    private boolean showLoginButton;
    private boolean showBackButton;

    
    public StageHandler(Stage stage) {
        this.stage = stage;
        this.label = "JOB LISTING";
        this.buttonText = "LOG IN";
        this.showLoginButton = true;
        this.showBackButton = false;
    }

    public void setHeader(String label,boolean showLoginButton,String buttonText, boolean showBackButton) {
        this.label = label;
        this.showLoginButton = showLoginButton;
        this.buttonText = buttonText;
        this.showBackButton = showBackButton;
    }

    public Pane getHeader() {
    Pane header = new Pane();
        header.setPrefSize(1000, 50);
        header.setStyle("-fx-background-color: #007bff;");
        Button backButton = new Button("Back");
        backButton.setVisible(this.getShowBackButton());
        backButton.setLayoutX(10);
        backButton.setLayoutY(15); 
        backButton.setStyle("-fx-background-color: #007bff;-fx-text-fill: white; -fx-font-size: 12px;");
        backButton.setOnAction(e -> {
            if(this.prevView != null) {
                StageHandler sh = new StageHandler(this.stage);
                sh.setScene(new Pane(this.prevView));
            }
            System.out.println(e);
        });
        Label headerLabel = new Label(this.getLabel());
        headerLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        if(!getShowBackButton())headerLabel.setLayoutX(20);
        else headerLabel.setLayoutX(60);
        headerLabel.setLayoutY(15);
        Button loginButton = new Button(this.getButtonText());
        loginButton.setVisible(this.getShowLoginButton());
        loginButton.setLayoutX(900);
        loginButton.setLayoutY(10);
        loginButton.setStyle("-fx-background-color: white; -fx-text-fill: #007bff; -fx-font-weight: bold;");
        loginButton.setOnAction(e -> { 
            Loginpage loginPage = new Loginpage(); 
            StageHandler sh = new StageHandler(this.stage);
            sh.prevView = this.currentView;
            sh.setHeader("Login", false, "", false);
            sh.setCurrentView(loginPage.getView());
            e.consume();
            
        });
        header.getChildren().addAll(headerLabel, loginButton, backButton);
        return header;
        
    }
    
    public String getButtonText() {
        return buttonText;
    }
    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public boolean getShowLoginButton() {
        return showLoginButton;
    }
    public void setShowLoginButton(boolean showLoginButton) {
        this.showLoginButton = showLoginButton;
    }
    public boolean getShowBackButton() {
        return showBackButton;
    }
    public void setShowBackButton(boolean showBackButton) {
        this.showBackButton = showBackButton;
    }

    public void setCurrentView(Pane view) {
        BorderPane completeView = new BorderPane();  
        view.setLayoutY(50); // Adjust layout to be below the header
        completeView.setTop(getHeader());
        completeView.setCenter(view);
        this.currentView = completeView;  
        setScene(this.getCurrentView());
    } 

    public Pane getCurrentView() {
        return currentView;
    } 

    public Scene getScene() {
        return scene;
    }

    public void setScene(Pane root) {
        this.scene =  new Scene(root,1000, 600);
        setStage(this.getScene());
    }
    public void setStage(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }
    public Stage getStage() {
        return stage;
    } 
    public void setPrevView(Pane prevView) {
        this.prevView = prevView;
    }
    
}