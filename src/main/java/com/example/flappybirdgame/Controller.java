package com.example.flappybirdgame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Pane pane;
    @FXML
    Rectangle bird;
    @FXML
    Label score;
    @FXML
    Text text;

    private double upSpeed = 60;
    private double downSpeed = .04;
    private double pathSpeed = 2;
    private double increment=0;
    private double incrementEachTime=.02;
    //Timelines
    private Timeline forDown,createPoles;

    private ArrayList<Rectangle> poleList = new ArrayList<>();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        goDown();
        Platform.runLater(this::initializePane);
    }

    private void initializePane() {
        pane.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE,null,null)));
        pane.getScene().setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.UP || keyEvent.getCode()==KeyCode.SPACE) {
                if (bird.getY() >= upSpeed) {
                    bird.setY(bird.getY() - upSpeed);
                } else {
                    bird.setY(0);
                }
                increment = 0;
            }
        });
        createPoleThread();
    }
    private void stopEveryThings(){
        forDown.stop();
        createPoles.stop();
        text.setVisible(true);
        pane.getScene().setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode() == KeyCode.P){
                text.setVisible(false);
                resetGame();
            }
        });
    }

    private void resetGame() {
        forDown.playFromStart();
        createPoles.playFromStart();
        pane.getChildren().removeAll(poleList);
        poleList.removeAll(poleList);
        score.setText("0");
        bird.setY(10);
        pane.getScene().setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode()==KeyCode.UP || keyEvent.getCode()==KeyCode.SPACE) {
                if (bird.getY() >= upSpeed) {
                    bird.setY(bird.getY() - upSpeed);
                } else {
                    bird.setY(0);
                }
                increment = 0;
            }
        });
    }

    private void createPoleThread() {
        createPoles=new Timeline(new KeyFrame(Duration.seconds(3),event -> {
            createPole();
        }));
        createPoles.setCycleCount(Timeline.INDEFINITE);
        createPoles.play();
    }

    private void createPole(){
        int x = (int)pane.getWidth()+10;
        int width = 35;
        int space=180;
        int minimumDownHeight=50; // minimum
        int maxUpperHeight = (int)pane.getHeight()-minimumDownHeight-space;
        int minimumUpperHeight=50;
        Random random = new Random();
        int upperHeight = random.nextInt(maxUpperHeight-minimumUpperHeight) + minimumUpperHeight;
        int downHeight = (int)pane.getHeight()-upperHeight-space;

        Rectangle upperPole = new Rectangle(x,0,width,upperHeight);
        Rectangle lowerPole = new Rectangle(x,upperHeight+space,width,downHeight);

        pane.getChildren().addAll(upperPole,lowerPole);

        poleList.add(upperPole);
        poleList.add(lowerPole);
    }
     private void movePath(){
        ArrayList<Rectangle> out = new ArrayList<>();
        //boolean b =true;
        for(var item: poleList){
            item.setX(item.getX()-pathSpeed);
            if(item.getX()<= -item.getWidth()-10){
                out.add(item);
            }
            if(item.getBoundsInParent().intersects(bird.getBoundsInParent())){
                stopEveryThings();
            }
            if(item.getX()+item.getLayoutX() == bird.getX()+ bird.getLayoutX()){
                score.setText((Float.parseFloat(score.getText())+.5)+"");
            }
        }
        pane.getChildren().removeAll(out);
        poleList.removeAll(out);
     }


    private void goDown() {
        forDown = new Timeline(new KeyFrame(Duration.millis(10),event->{
            if(bird.getY()+bird.getHeight()<pane.getHeight()) {
                bird.setY(bird.getY() + downSpeed + increment);
                increment += incrementEachTime;
            }
            else{
                bird.setY(pane.getHeight()-bird.getHeight());
            }
            movePath();
        }));
        forDown.setCycleCount(Timeline.INDEFINITE);
        forDown.play();
    }


}