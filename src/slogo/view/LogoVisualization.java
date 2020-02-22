package slogo.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

public class LogoVisualization {

    Pane root = new Pane();
    BorderPane border = new BorderPane();
    Stage myStage;
    private TurtleWindow graphics;
    ViewController myController;

    public LogoVisualization(Stage stage)
    {
        myStage = stage;
        init();
    }

    public void setBackgroundColor(String color)
    {
        graphics.setBackgroundColor(color);
    }

    public void init()
    {

        ConsoleWindow commandWindow = new ConsoleWindow();
        VariableWindow myVariables = new VariableWindow();
        HistoryWindow myHistory = new HistoryWindow();
        AvailableCommandsWindow available = new AvailableCommandsWindow("resources.languages.English");
        graphics = new TurtleWindow();
        Menu toolbar = new Menu();

        VBox leftComps = new VBox();
        leftComps.getChildren().addAll(myHistory.getView(),available.getView());

        HBox bottom  = new HBox();
        bottom.getChildren().addAll(myVariables.getView(),commandWindow.getView());


        border.setBottom(bottom);
        border.setCenter(graphics.getView());
        border.setLeft(leftComps);
        border.setTop(toolbar.getView());


        Scene scene = new Scene(border,1000,1000);
        myStage.setScene(scene);
        myStage.show();
        graphics.fitCanvas();



    }




}
