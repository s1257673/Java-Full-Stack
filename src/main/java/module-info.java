module com.example.flappybirdgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.flappybirdgame to javafx.fxml;
    exports com.example.flappybirdgame;
}