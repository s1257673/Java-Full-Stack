package com.example.javafxproject.BrickBriker;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.stream.Collectors;

public class Controller implements Initializable {
    @FXML
    private Pane pane;
    @FXML
    private Circle boll;
    @FXML
    private Rectangle base;

    private int totalBlocks;

    private double movingSpeed = 5;

    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private int score;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image image = new Image("BrickBrokenBackGround.jpeg",pane.getWidth(),pane.getHeight(),false,true);
//        pane.setBackground(new Background(new BackgroundImage(image,BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true)
//        )));
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));

        rectangles.addAll(
                pane.getChildren().stream()  // i want to skip base Rectangle how i can do it..
                        .filter(node -> node instanceof Rectangle && node!=base)
                        .map(node -> (Rectangle) node)
                        .collect(Collectors.toCollection(ArrayList::new))
        );
//        rectangles.remove(rectangles.getLast());
        totalBlocks=rectangles.size();
        startMoving();
        Platform.runLater(this::setKeyEvent);
    }

    private double move = 110;
    @FXML
    Label label;

    private void setKeyEvent() {
        pane.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case KeyCode.UP:
                    System.out.println("UP Pressed");
                    if (moveBase.getStatus() == Animation.Status.RUNNING) {
                        moveBase.stop();
                        bollUp();
                    }
                    break;
                case KeyCode.LEFT:
                    System.out.println("Left Pressed");
                    if (moveBase.getStatus() == Animation.Status.STOPPED && base.getLayoutX() > 0) {
                        base.setLayoutX(base.getLayoutX() - move);
                        if (base.getLayoutX() < 0)
                            base.setLayoutX(0);
                    }
                    break;
                case KeyCode.RIGHT:
                    if (moveBase.getStatus() == Animation.Status.STOPPED && (base.getLayoutX() + base.getWidth()) < pane.getWidth()) {
                        base.setLayoutX(base.getLayoutX() + move);
                        if ((base.getLayoutX() + base.getWidth()) > pane.getWidth())
                            base.setLayoutX(pane.getWidth() - base.getWidth());
                    }
                    break;
                case KeyCode.ESCAPE:
                    Animation.Status isPauseBase = moveBase.getStatus();
                    pause();
                    Alert alert =  new Alert(Alert.AlertType.CONFIRMATION);
                    ButtonType  buttonType = new ButtonType("Resume");
                    ButtonType buttonType1 = new ButtonType("Exit");

                    alert.getButtonTypes().setAll(buttonType,buttonType1);

                    if(alert.showAndWait().get() == buttonType1 ){
                        ((Stage)pane.getScene().getWindow()).close();
                    }
                    else{

                        label.setText("3");
//                        Animation.Status isPauseBase = moveBase.getStatus();
                        Timeline timer = new Timeline(new KeyFrame(Duration.seconds(1),temp->{
                            if(label.getText().equals("1")){
                                label.setText("");
                                bollHandler.play();
                                if(isPauseBase == Animation.Status.RUNNING)
                                    moveBase.play();
                            }
                            else {
                                label.setText(Integer.parseInt(label.getText()) - 1 + "");
                            }
                        }));
                        timer.setCycleCount(3);
                        timer.play();
                    }

            }
        });
    }

    private void pause() {
        bollHandler.pause();
        moveBase.pause();
    }

    private double bollSpeedx = +6.5;
    private double bollSpeedy = -10;
    private Timeline bollHandler;

    private void bollUp() {
        bollHandler = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            // System.out.println(boll.getCenterY());
            boll.setLayoutX(boll.getLayoutX() + bollSpeedx);
            boll.setLayoutY(boll.getLayoutY() + bollSpeedy);
            //System.out.println(boll.getCenterY());
            checkCollision();
        }));
        bollHandler.setCycleCount(Timeline.INDEFINITE);
        bollHandler.play();
    }

    private void checkCollision() {
        //up
        if (boll.getLayoutY() <= boll.getRadius()) {
            System.out.println("Up Condition Hit");
            bollSpeedy = Math.abs(bollSpeedy);
            bollSpeedx = bollSpeedx>0  ? (bollSpeedx<15) ? bollSpeedx+4 : 0 : (bollSpeedx>-15) ? bollSpeedx-4 : 0;
        }
        //left-right
//        if (boll.getLayoutX() <= boll.getRadius() || (boll.getLayoutX()+boll.getRadius()) >= pane.getWidth()) {
//            System.out.println("left-right");
//            bollSpeedx = -1 * bollSpeedx;
//        }
        if (boll.getBoundsInParent().getMinX() <= 0 || boll.getBoundsInParent().getMaxX() >= pane.getWidth()) {
            bollSpeedx = -1 * bollSpeedx;
          //  bollSpeedx = bollSpeedx>0  ? (bollSpeedx<35) ? bollSpeedx+8 : 0 : (bollSpeedx>-35) ? bollSpeedx-8 : 0;
//            boll.setLayoutX(boll.getLayoutX()+15);
//            boll.setLayoutY(boll.getLayoutY()+15);
        }
//base
        if (boll.getBoundsInParent().intersects(base.getBoundsInParent())) {
            bollSpeedy = -Math.abs(bollSpeedy);
        }
        for(var rect : rectangles){
            if(rect.getBoundsInParent().intersects(boll.getBoundsInParent())){
                pane.getChildren().remove(rect); // Remove the rectangle from the pane
                rectangles.remove(rect);
                bollSpeedy = -1 * bollSpeedy;
                score++;
                bollSpeedx = bollSpeedx>0  ? (bollSpeedx<15) ? bollSpeedx+4 : 0 : (bollSpeedx>-15) ? bollSpeedx-4 : 0;
                break;
            }
        }
//out
        if (boll.getLayoutY() >= pane.getHeight() + boll.getRadius()) {
            pause();
            imageView.setVisible(true);
            ScaleTransition transition = new ScaleTransition(Duration.seconds(2),imageView);
            transition.setByX(2);
            transition.setByY(2);
            transition.play();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameOverScene.fxml"));
                Parent root = loader.load();
                Controller2 controller = loader.getController();
                controller.score.setText(score+"");
                controller.remain.setText(totalBlocks - score+"");
                Scene scene = new Scene(root);
                Stage stage = (Stage)pane.getScene().getWindow();
                stage.setFullScreen(true);
                controller.setStage(stage);
                stage.setScene(scene);
            }catch(Exception e){
                System.out.println("Exception Occured");
            }
            System.out.println(score);
        }
    }
    @FXML
    ImageView imageView;

    private Timeline moveBase;

    private void startMoving() {
        moveBase = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            base.setLayoutX(base.getLayoutX() + movingSpeed);
            boll.setCenterX(boll.getCenterX() + movingSpeed);
            updateDirection();
        }));
        moveBase.setCycleCount(Timeline.INDEFINITE);
        moveBase.play();
    }

    private void updateDirection() {
        if (base.getLayoutX() + base.getWidth() >= pane.getWidth()) {
            movingSpeed = -Math.abs(movingSpeed);
        } else if (base.getLayoutX() <= 0) {
            movingSpeed = Math.abs(movingSpeed);
        }
    }

}
