package com.algoma.ears.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public final class Card extends VBox {

    private Long Id;
    private String title;
    private String description;
    private VBox textContainer;
    private HBox ButtonContainer;
     

    public Card(){
        super(10);
        this.setPadding(new Insets(15));
        this.setAlignment(Pos.CENTER);
        this.setPrefHeight(100);
        this.setPrefWidth(760);
        this.setStyle("-fx-border-color: #cccccc; -fx-border-radius: 5; -fx-background-color: #f7f7f7; -fx-padding: 10;");
        this.textContainer  = new VBox(5);
        this.ButtonContainer = new HBox(2);
    }

    public Button addButton(String buttonText){
        Button bt = new Button(buttonText);
        // bt.setOnAction(e->{return;});
        this.ButtonContainer.getChildren().add(bt);
        return bt;
    }

    public Long getCardId() {
        return this.Id;
    }

    public void setCardId(Long Id) {
        this.Id = Id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VBox getCard(){
        
       
        Label cardId = new Label("ID:"+ this.getCardId()); 
        Label title  = new Label(this.getTitle());
        Label description = new Label(this.getDescription());
        this.textContainer.getChildren().addAll (title , cardId, description, this.ButtonContainer);
        this.getChildren().add(textContainer);
        return this;
    }
    
}
