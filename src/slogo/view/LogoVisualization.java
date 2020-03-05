package slogo.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import slogo.controller.ParserController;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogoVisualization extends BorderPane{

    private static final String UI_TEXT = "resources.UIText";
    private static final String CSS_FILE = "/resources/uistyle.css";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);
    //private BorderPane border = new BorderPane();
    private Stage myStage;
    private CodeStage myCode;
    private TurtleWindow graphics;
    private ParserController myController;
    private PaletteWindow myPalette;
    private MoveTurtleWindow turtleMover;
    private Property activePenColor;
    private Property activeTurtleImage;
    private SimpleBooleanProperty updateNeeded;
    private VariableWindow myVariables;
    private ConsoleWindow myConsole;
    private HistoryWindow myHistory;
    private Menu toolbar;
    private TurtleCompleteInfoWindow myTurtleInfo;
    private AvailableCommandsWindow available;
    private OutputWindow myOutput;
    private Double output;
    private List<Object> parameters;

    public LogoVisualization(ParserController control)
    {
        //myStage = stage;
        myController = control;
        parameters = new ArrayList<>();
        init();
    }



    private void init()
    {
        myCode = new CodeStage();


        updateNeeded = new SimpleBooleanProperty();
        updateNeeded.setValue(false);
        updateNeeded.addListener(((observable, oldValue, newValue) -> checkUpdate(newValue)));

        toolbar = new Menu(myController,updateNeeded);

        fillParameters();

        myConsole = new ConsoleWindow(myController,updateNeeded,myCode);
        myVariables = new VariableWindow(myController,updateNeeded,myCode);
        myHistory = new HistoryWindow(myController,updateNeeded,myCode);
        available = new AvailableCommandsWindow(toolbar.getActiveLanguage(),myController,updateNeeded,myCode);
        graphics = new TurtleWindow(toolbar.getActiveBackgroundColor(),toolbar.getActiveTurtleImage(),myController,toolbar.getActivePenColor());
        turtleMover = new MoveTurtleWindow(myController,updateNeeded,myCode);
        myPalette = new PaletteWindow(myController,updateNeeded);
        myTurtleInfo = new TurtleCompleteInfoWindow(myController);
        myOutput = new OutputWindow();



        VBox leftComps = new VBox();
        leftComps.getChildren().addAll(myHistory.getView(),available.getView(),myVariables.getView(),myOutput.getView());

        HBox bottom  = new HBox();
        bottom.getChildren().addAll(myConsole.getView());
        bottom.setMaxHeight(50);

        VBox rightComps = new VBox();
        rightComps.getChildren().addAll(turtleMover.getView(),myPalette.getView(),myTurtleInfo.getView());

        bottom.setAlignment(Pos.CENTER);

        this.setCenter(graphics.getView());
        this.setBottom(bottom);
        BorderPane.setAlignment(bottom, Pos.CENTER);

        this.setLeft(leftComps);
        this.setTop(toolbar.getView());
        this.setRight(rightComps);



        /*
        Scene scene = new Scene(border,1000,1000);
        scene.getStylesheets().add(getClass().getResource(CSS_FILE).toExternalForm());
        myStage.setScene(scene);
        myStage.show();

         */


    }

    private void fillParameters()
    {
        parameters.add(myController);
        parameters.add(updateNeeded);
        parameters.add(myCode);
        parameters.add(toolbar.getActiveLanguage());
    }

    private void checkUpdate(boolean check)
    {
        if(check)
        {
            updateAllPanes();
        }
    }

    private void updateAllPanes()
    {
        try {
             output = myController.parseCode(myCode.getCodeToBeParsed());
        }
        catch (Exception e)
        {
            showError(e.getMessage());
        }

        myOutput.setOutput(output);

        graphics.update();
        myHistory.update();
        myVariables.update();
        myPalette.update();
        myTurtleInfo.update();
        toolbar.update();
        myOutput.update();


        myCode.clearStagedCode();
        updateNeeded.setValue(false);

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        //alert.setTitle(myResources.getString("ErrorTitle"));
        alert.setContentText(message);
        alert.showAndWait();
    }






}
