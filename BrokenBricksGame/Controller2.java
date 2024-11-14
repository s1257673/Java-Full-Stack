package com.example.javafxproject.BrickBriker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import java.lang.classfile.Label;

public class Controller2  {
    @FXML
    Label score,remain;

    Stage stage;
    public void playAgain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BrickBriker.fxml"));
            if(loader==null)
                System.out.println("Loader is null");
            Parent root = loader.load();
            if(root==null)
                System.out.println("Root is also null");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void exit(ActionEvent event) {
        stage.close();
    }

    public  void setStage(Stage stage){
        this.stage=stage;
    }

}
