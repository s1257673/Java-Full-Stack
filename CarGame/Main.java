package com.example.javafxproject.CarGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//import java.awt.event.KeyEvent;

public class Main extends Application {
    public static void main(String... args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CarGame.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image("/gameLogo.jpeg"));

        stage.setTitle("My Car Game");

        stage.setScene(scene);

//        stage.setFullScreen(true);
        stage.setResizable(false);
//        scene.setOnKeyPressed(event ->{
//            //scene.setFill(Color.BLACK);
//            switch(event.getCode()){
//                case KeyCode.UP :
//                    controller.increasingSpeed(event);
//                    break;
//                case KeyCode.DOWN:
//                    controller.decreaseSpeed(event);
//                    break;
//                case KeyCode.LEFT:
//                    controller.left(event);
//                    break;
//                case KeyCode.RIGHT:
//                    controller.right(event);
//                    break;
//                case KeyCode.ESCAPE:
//                    controller.stopEverything();
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setTitle("Exit");
//                    alert.setContentText("Do you want to Continue");
//                    ButtonType exit = new ButtonType("Exit");
//                    ButtonType play = new ButtonType("Continue/Play");
//
//                    alert.getButtonTypes().setAll(exit,play);
//                    if(alert.showAndWait().get() == exit){
//                        stage.close();
//                    }
//                    else{
//                        controller.startAgain();
//                    }
//            }
//        });
        stage.setOnCloseRequest(event->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit Confirmation");
            alert.setContentText("Do you want to exit");
            controller.stopEverything();
            if(alert.showAndWait().get() == ButtonType.OK){
                stage.close();
            }
            else{
                event.consume();
                controller.startAgain();
            }
        });
        controller.stage = stage;
        stage.show();
    }
}
