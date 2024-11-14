package com.example.javafxproject.CarGame;

import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

//import java.lang.classfile.Label;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

//import java.awt.event.KeyEvent;

public class Controller implements Initializable {
    private double speed = 0;
    @FXML
    ImageView myCar;
    @FXML
    ImageView road;
    @FXML
    ImageView road2;

    @FXML
    ImageView car1,car2,car3,car4,car5,car6,car7,car8;

    @FXML
    Pane pane;

    private Parent root;
    private  Scene scene;
    Stage stage;

    @FXML
    Label score,maxScore;

    private int sc,maxsc;

    private Timeline roadTimeline;
    private Timeline setEnemyCars;
    private Timeline collisionTimeline;

//    private Image[] imageArr = {new Image("/Car1.png") , new Image("Car2.png") , new Image("Car3.jpeg"),
//            new Image("Car4.jpeg"),
//            new Image("Car5.jpeg")
//    };
    void left(KeyEvent keyEvent){
        if(myCar.getLayoutX()>230){
            myCar.setLayoutX(myCar.getLayoutX()-myCar.getFitWidth()-15);
          //  myCar.setLayoutX(myCar.getLayoutX()-10);
        }
    }
    void right(KeyEvent keyEvent){
        if(myCar.getLayoutX()<350){
//            myCar.setLayoutX(myCar.getLayoutX()+10);
            myCar.setLayoutX(myCar.getLayoutX()+myCar.getFitWidth()+15);
        }
    }
    void increasingSpeed(KeyEvent keyEvent){
        if(speed<50)
            speed+=.5;
        System.out.println(speed);
    }
    void decreaseSpeed(KeyEvent keyEvent){
        if(speed>0 && speed>=5){
            speed-=5;
        }
        else{
            speed=0;
        }
        System.out.println(speed);
    }
    void fraction(){
        if(speed>0){
            speed-=.5;
        }
        else{
            speed=0;
        }
        System.out.println(speed);
    }
    private Timeline fraction;
    private void startFrictionTimer() {
        fraction = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> fraction())
        );
        fraction.setCycleCount(Timeline.INDEFINITE);
        fraction.play();
    }
    private void addKeyEvent(){
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CarGame.fxml"));
//        try{
//            loader.load();
//        }
//        catch(Exception e){System.out.println("Error Occured");}
     //   Controller controller = loader.getController();
        scene = pane.getScene();
        scene.setOnKeyPressed(event ->{
            //scene.setFill(Color.BLACK);
            switch(event.getCode()){
                case KeyCode.UP :
                    increasingSpeed(event);
                    break;
                case KeyCode.DOWN:
                    decreaseSpeed(event);
                    break;
                case KeyCode.LEFT:
                    left(event);
                    break;
                case KeyCode.RIGHT:
                    right(event);
                    break;
                case KeyCode.ESCAPE:
                    stopEverything();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Exit");
                    alert.setContentText("Do you want to Continue");
                    ButtonType exit = new ButtonType("Exit");
                    ButtonType play = new ButtonType("Continue/Play");

                    alert.getButtonTypes().setAll(exit,play);
                    if(alert.showAndWait().get() == exit){
                        stage.close();
                    }
                    else{
                        startAgain();
                    }
            }
        });
    }
    private void startRoadAnimation() {
        road.setLayoutY(0);
        road2.setLayoutY(-road.getFitHeight());

        roadTimeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            if(timer.getStatus() == Animation.Status.STOPPED) {
                if (speed > 0) {
                    road.setLayoutY(road.getLayoutY() + speed);
                    road2.setLayoutY(road2.getLayoutY() + speed);
                }
                if (road.getLayoutY() >= road.getFitHeight()) {
                    road.setLayoutY(road2.getLayoutY() - road.getFitHeight());
                }

                if (road2.getLayoutY() >= road.getFitHeight()) {
                    road2.setLayoutY(road.getLayoutY() - road2.getFitHeight());
                }
            }
        }));
        roadTimeline.setCycleCount(Timeline.INDEFINITE);
        roadTimeline.play();
    }
    void setEnemyCar(){
        car1.setLayoutY(-100);
        car2.setLayoutY(-road.getFitHeight());
        car3.setLayoutY(-100);
        car4.setLayoutY(-road.getFitHeight());
        car5.setLayoutY(-100);
        car6.setLayoutY(-road.getFitHeight());
        car7.setLayoutY(-100);
        car8.setLayoutY(-road.getFitHeight());
        setEnemyCars = new Timeline(new KeyFrame(Duration.millis(20),event->{
            if(timer.getStatus() == Animation.Status.STOPPED) {
                car1.setLayoutY(car1.getLayoutY() + .6 + speed);
                car2.setLayoutY(car2.getLayoutY() + .6 + speed);

                car3.setLayoutY(car3.getLayoutY() + 1.5 + speed);
                car4.setLayoutY(car4.getLayoutY() + 1.5 + speed);

                car5.setLayoutY(car5.getLayoutY() + 2.5 + speed);
                car6.setLayoutY(car6.getLayoutY() + 2.5 + speed);

                car7.setLayoutY(car7.getLayoutY() + .9 + speed);
                car8.setLayoutY(car8.getLayoutY() + .9 + speed);

                if (car1.getLayoutY() >= 348 + car1.getFitHeight() && car2.getLayoutY() >= 348 + car2.getFitHeight()) {
                    car1.setLayoutY(-500);
                }
                if (car2.getLayoutY() >= 348 + car2.getFitHeight()) {
                    car2.setLayoutY(-340);
                }
                if (car3.getLayoutY() >= 348 + car3.getFitHeight() && car4.getLayoutY() >= 348 + car4.getFitHeight()) {
                    car3.setLayoutY(-200);
                }
                if (car4.getLayoutY() >= 348 + car4.getFitHeight()) {
                    car4.setLayoutY(-340);
                }
                if (car5.getLayoutY() >= 348 + car5.getFitHeight() && car6.getLayoutY() >= 348 + car6.getFitHeight()) {
                    car5.setLayoutY(-700);
                }
                if (car6.getLayoutY() >= 348 + car6.getFitHeight()) {
                    car6.setLayoutY(-340);
                }
                if (car7.getLayoutY() >= 348 + car7.getFitHeight() && car8.getLayoutY() >= 348 + car8.getFitHeight()) {
                    car7.setLayoutY(-420);
                }
                if (car8.getLayoutY() >= 348 + car8.getFitHeight()) {
                    car8.setLayoutY(-340);
                }
            }

        }));
        setEnemyCars.setCycleCount(Timeline.INDEFINITE);
        setEnemyCars.play();
    }
    private void scoring() {
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            // Update score based on each car's position
            checkAndScore(car1);
            checkAndScore(car2);
            checkAndScore(car3);
            checkAndScore(car4);
            checkAndScore(car5);
            checkAndScore(car6);
            checkAndScore(car7);
            checkAndScore(car8);
        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }

    // Helper method to check each car's position and update score
    private void checkAndScore(ImageView car) {
        if (car.getLayoutY() >= myCar.getLayoutY() + myCar.getFitHeight() &&
                car.getLayoutY() <= myCar.getLayoutY() + myCar.getFitHeight() + speed) {

            // Increment the score
            sc++;
            if (sc > maxsc) maxsc = sc;

            // Update score display
            score.setText(String.valueOf(sc));
            maxScore.setText(String.valueOf(maxsc));

            // Reset the car's position for another cycle
            resetEnemyPosition(car);
        }
    }

    // Method to reset the car's position to the top for replaying
    private void resetEnemyPosition(ImageView car) {
        car.setLayoutY(-road.getFitHeight()); // Adjust as per your initial starting height
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)  {

        stopEverything();
        startTimer();

        startRoadAnimation();
        startFrictionTimer();
        setEnemyCar();
        outCondition();

        scoring();
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK,null,null)));
        Platform.runLater(this::addKeyEvent);
    }
    private Timeline tl;
    @FXML
    ImageView gameOver;
    private void outCondition()  {
        tl = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            if (myCar.getBoundsInParent().intersects(car1.getBoundsInParent()) ||
                    myCar.getBoundsInParent().intersects(car2.getBoundsInParent()) ||
                    myCar.getBoundsInParent().intersects(car3.getBoundsInParent()) ||
                    myCar.getBoundsInParent().intersects(car4.getBoundsInParent()) ||
                    myCar.getBoundsInParent().intersects(car5.getBoundsInParent()) ||
                    myCar.getBoundsInParent().intersects(car6.getBoundsInParent()) ||
                    myCar.getBoundsInParent().intersects(car7.getBoundsInParent()) ||
                    myCar.getBoundsInParent().intersects(car8.getBoundsInParent())) {
                gameOver.setImage(new Image("/GameOver.png"));
                ScaleTransition transition = new ScaleTransition(Duration.seconds(1),gameOver);
                transition.setByX(1.2);
                transition.setByY(1.2);
                transition.play();

                switchToGameOver();
            }


        }));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
    }
    void switchToGameOver(){
        stopEverything();
        PauseTransition pause = new PauseTransition(Duration.seconds(2.3));
        pause.setOnFinished(tempevent-> {

            try {
                FXMLLoader loader  = new FXMLLoader(getClass().getResource("/carGame2.fxml"));
                root = loader.load();
                Controller2 controller = loader.getController();
                controller.score.setText(sc + "");
                controller.maxScore.setText(maxsc + "");
                scene = new Scene(root);
                // stage = new Stage();
                if(stage==null)
                    stage = (Stage)pane.getScene().getWindow();
                stage.setScene(scene);
                //  stage.show();
            } catch (Exception e) {
                System.out.println("Exception Occured");
                System.out.println(e);
            }//System.out.println("Game Over!");
            //remainTime.setText("Game Over!");
        });
        pause.play();

    }
    private double prevSpeed;
    void stopEverything(){
        prevSpeed=speed;
        speed=0;
        if(roadTimeline!=null)
            roadTimeline.stop();
        if(setEnemyCars!=null)
            setEnemyCars.stop();
        if(tl!=null)
            tl.stop();
        if(fraction!=null)
             fraction.stop();
    }

    @FXML
    Label remainTime;

   // Timeline breakTimeLine;
    void startAgain(){
        //prevSpeed=speed;
        // breakTimeLine = new Timeline(new KeyFrame(Duration.seconds(5),event->{
        startTimer();


        //        }));
//        breakTimeLine.setCycleCount(1);
//        breakTimeLine.play();
    }
    Timeline timer;
     void startTimer() {
        remainTime.setText("3");
        timer = new Timeline(new KeyFrame(Duration.seconds(1),event->{
            if(remainTime.getText().equals("GO")){
                remainTime.setText("");
            }
            else if(remainTime.getText().equals("1")){
                remainTime.setText("GO");
                speed = prevSpeed;
                roadTimeline.playFromStart();
                setEnemyCars.playFromStart();
                tl.play();

            }
            else {
                remainTime.setText(Integer.parseInt(remainTime.getText()) - 1 + "");
            }
        }));
        timer.setCycleCount(4);
        timer.play();

    }
}
