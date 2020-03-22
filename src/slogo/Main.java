package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.view.components.MainVisualization;

/**
 * @author Saurav Sanjay
 * Starts Logo program
 */
public class Main extends Application {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }


    /**
     * Creates a new MainVisualization
     * @param primaryStage stage for display
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        MainVisualization myMain = new MainVisualization(primaryStage);


    }
}
