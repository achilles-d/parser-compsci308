package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.view.components.MainVisualization;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage) throws Exception {
        MainVisualization myMain = new MainVisualization(primaryStage);


    }
}
