package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.controller.ParserController;
import slogo.view.LogoVisualization;
import slogo.view.MainWindow;

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
        ParserController controller = new ParserController();
        LogoVisualization myVis = new LogoVisualization(controller);
        MainWindow myMain = new MainWindow(primaryStage);
        myMain.addWorkspace(myVis);


    }
}
