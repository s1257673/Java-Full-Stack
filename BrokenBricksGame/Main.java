package com.example.javafxproject.BrickBriker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/BrickBriker.fxml"));
        Scene scene = new Scene(root);

        stage.getIcons().add(new Image("BrickBrikerLogo.jpeg"));
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.setFullScreenExitHint("Press q for exit full screen");
        stage.setFullScreenExitKeyCombination(KeyCombination.valueOf("q"));
        stage.setTitle("Broken Bricks");
        stage.setOnCloseRequest(event->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Exit/Continue");
            alert.setContentText("Do you want Exit");
            if(alert.showAndWait().get() == ButtonType.OK){
                stage.close();
            }
            event.consume();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
