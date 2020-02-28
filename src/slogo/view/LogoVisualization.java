package slogo.view;

import javafx.beans.property.Property;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import slogo.controller.ParserController;

import java.util.ResourceBundle;

public class LogoVisualization extends BorderPane{

    private static final String UI_TEXT = "resources.UIText";
    private static final String EXECUTE = "execute";
    private static final String CSS_FILE = "/resources/uistyle.css";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);
    //private BorderPane border = new BorderPane();
    private Stage myStage;
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

    public LogoVisualization(ParserController control)
    {
        //myStage = stage;
        myController = control;
        init();
    }

    public void setBackgroundColor(String color)
    {
        graphics.setBackgroundColor(color);
    }

    public void init()
    {
        executeButton = new Button(visualText.getString(EXECUTE));
        executeButton.setOnAction(event -> {updateAllPanes();});

        toolbar = new Menu(myController);
        myConsole = new ConsoleWindow(executeButton,myController);
        myVariables = new VariableWindow(myController);
        myHistory = new HistoryWindow(myController);
        available = new AvailableCommandsWindow(toolbar.getActiveLanguage(),myController);
        graphics = new TurtleWindow(toolbar.getActiveBackgroundColor(),toolbar.getActiveTurtleImage(),myController,toolbar.getActivePenColor());

        VBox leftComps = new VBox();
        leftComps.getChildren().addAll(myHistory.getView(),available.getView(),myVariables.getView());

        HBox bottom  = new HBox();
        bottom.getChildren().addAll(myConsole.getView());


        this.setCenter(graphics.getView());
        this.setBottom(bottom);
        this.setLeft(leftComps);
        this.setTop(toolbar.getView());



        /*
        Scene scene = new Scene(border,1000,1000);
        scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        myStage.setScene(scene);
        myStage.show();

         */


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
        myHistory.update();
        myVariables.update();

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }






}
