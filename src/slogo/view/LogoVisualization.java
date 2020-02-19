package slogo.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LogoVisualization extends Application {

    Group root = new Group();
    public void start(Stage stage)
    {

        Scene scene = new Scene(root,700,700);
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
