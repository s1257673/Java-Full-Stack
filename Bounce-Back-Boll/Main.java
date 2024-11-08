package com.example.javafxproject.DVDAnimations;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//import javax.swing.text.Element;
//import javax.swing.text.html.ImageView;
//import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Animation.fxml"));
        stage.getIcons().add(new Image("dvd.png"));
        Parent root = loader.load();
        Scene scene  = new Scene(root);
        scene.setFill(Color.BLACK);
        stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
