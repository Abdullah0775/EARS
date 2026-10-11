package com.algoma.ears.ui;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class NavCol extends VBox {
    public NavCol(){
        super();
        this.setPrefWidth(200);
        this.setStyle("-fx-background-color: #084994ff;");
    }
    public Button addItem(String label){
        Button bt = new Button(label);
        bt.setPrefSize(200, 50);
        bt.setStyle("-fx-background-color: #084994ff; -fx-text-fill: white;-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10px; -fx-border-width: 0px; -fx-border-color: transparent;");
        this.getChildren().add(bt);
        return bt;
    }
}
