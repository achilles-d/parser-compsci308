package slogo.view;

import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
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
import slogo.controller.ParserController;

import java.lang.reflect.Constructor;

public class LogoVisualization {

    Pane root = new Pane();
    BorderPane border = new BorderPane();
    Stage myStage;
    private TurtleWindow graphics;
    private ParserController myController;
    private Property activePenColor;
    private Property activeTurtleImage;
    private VariableWindow myVariables;
    private ConsoleWindow myConsole;
    private HistoryWindow myHistory;
    private Menu toolbar;
    AvailableCommandsWindow available;
    private Button executeButton;

    public LogoVisualization(Stage stage, ParserController control)
    {
        myStage = stage;
        myController = control;
        init();
    }

    public void setBackgroundColor(String color)
    {
        graphics.setBackgroundColor(color);
    }

    public void init()
    {

        executeButton = new Button("Execute");
        executeButton.setOnAction(event -> {updateAllPanes();});

        myConsole = new ConsoleWindow(executeButton,myController);

        myVariables = new VariableWindow(myController);
        myHistory = new HistoryWindow(myController);
        available = new AvailableCommandsWindow("resources.languages.English",myController);
        toolbar = new Menu(myController);
        activeTurtleImage = toolbar.getActiveTurtleImage();
        activePenColor = toolbar.getActivePenColor();
        graphics = new TurtleWindow(toolbar.getActivePenColor(),toolbar.getActiveTurtleImage(),myController);

        VBox leftComps = new VBox();
        leftComps.getChildren().addAll(myHistory.getView(),available.getView());

        HBox bottom  = new HBox();
        bottom.getChildren().addAll(myVariables.getView(),myConsole.getView());


        //border.setCenter(graphics.getView());
        border.setCenter(graphics.getView());
        border.setBottom(bottom);

        border.setLeft(leftComps);
        border.setTop(toolbar.getView());




        Scene scene = new Scene(border,1000,1000);
        myStage.setScene(scene);
        myStage.show();

        graphics.getSize();
        



    }

    private void updateAllPanes()
    {
        try {
            myController.parseCode(myConsole.getConsoleText());
        }
        catch (Exception e)
        {
            showError(e.getMessage());
        }

        graphics.update();

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }






}
