package com.example.javafxproject.DVDAnimations;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ImageView imageView;
    @FXML
    private Pane pane;


//    private double velocityX = 5.0;
//    private double velocityY = 5.0;
    private int x=5;
    private int y=5;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                imageView.setLayoutX(imageView.getLayoutX() + x);
                imageView.setLayoutY(imageView.getLayoutY() + y);
                updateBound();
            }
        };
        timer.start();
    }

    private void updateBound() {
        if(imageView.getFitWidth()+ imageView.getLayoutX() >= pane.getWidth()){
            x = -Math.abs(x);
        }
        if( imageView.getLayoutX() <=0){
            x = Math.abs(x);
        }
        if(imageView.getFitHeight()+ imageView.getLayoutY() >= pane.getHeight()){
            y = -Math.abs(x);
        }
        if(imageView.getLayoutY()<=0){
            y = Math.abs(x);
        }

    }
}
