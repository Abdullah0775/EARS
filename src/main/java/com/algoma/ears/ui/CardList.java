package com.algoma.ears.ui;

import java.util.List;

import com.algoma.ears.Faculty;
import com.algoma.ears.*;

import javafx.scene.layout.VBox;

public class CardList {
//Facing problem in creating button functions for the cards 
//thought of a solutions : 
//copy this functin in adminService and FacultyService class so that each class can handle their respective clicks 
//which also solves the card button clicks 
//have to think about the clicks on edit buttons which opens a edit page for that specific item 
// or should I do it in a way that handles all the classes for edits like jobs, faculty , profile
//Have to think for the reviews, applications and jobs view

     public static VBox createCardList(List<?> items) {
        VBox container = new VBox(10);
        container.setStyle("-fx-padding: 10px;");
        if(items.get(0)instanceof  Faculty){
            for(Object item: items ){
                Card card = new Card();
                Faculty fac = (Faculty) item;
                card.setTitle(fac.getName() + " - " + fac.getSubject());
                card.setCardId(fac.getId());
                card.addButton("Edit");
                card.addButton("Delete");
                container.getChildren().add(card.getCard());
            }
            Card card = new Card();
            card.setTitle("Create New Faculty");
            card.addButton(" +  ADD");
            container.getChildren().add(card.getCard());
        }
        else if(items.get(0) instanceof Jobs){
            for(Object item: items ){
                Card card = new Card();
                Jobs job = (Jobs) item;
                card.setTitle(job.getTitle() );
                card.setCardId(job.getId());
                card.addButton("Edit");
                card.addButton("Delete");
                container.getChildren().add(card.getCard());
            }
            Card card = new Card();
            card.setTitle("Create New Job");
            card.addButton(" +  ADD");
            container.getChildren().add(card.getCard());
        }
        else if(items.get(0) instanceof Application){
            for(Object item: items ){
                Card card = new Card();
                Application app = (Application) item;
                card.setTitle(app.getJobId() + " - " + app.getStatus());
                card.setCardId(app.getId());
                card.addButton("Change Status");
                card.addButton("View Reviews").setOnAction(e->{
                    //Get the list of reviews for this application for the job - 
                    //call this function again to get Vbox of reviews with the Job and application on the top 
                    //return the vbox to the admin/ faculty page to be set in the center pane
                });;
                container.getChildren().add(card.getCard());
            }
        }
        return container;
    }
}
