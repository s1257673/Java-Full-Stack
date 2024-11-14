package com.example.javafxproject.CarGame;

import javafx.animation.FadeTransition;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

    @FXML
    private Pane pane;

    @FXML
    private Text text;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        FadeTransition transition = new FadeTransition(Duration.seconds(2), text);
        transition.setCycleCount(Transition.INDEFINITE);
        transition.setFromValue(1);
        transition.setToValue(0.1);
        transition.setAutoReverse(true);
        transition.play();
    }
    @FXML
    Label score;
    @FXML
    Label maxScore;
    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void play(Event event) {
//        System.out.println("Pressed Continue");
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/CarGame.fxml"));
            Stage stage = (Stage)pane.getScene().getWindow();
            stage.setScene(new Scene(root));
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
