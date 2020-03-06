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
    private static final String LEFT_COMPONENTS = "Left";
    private static final String RIGHT_COMPONENTS = "Right";
    private static final String ORDER_COMPONENTS = "resources.OrderUIElements";


    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);
    private ResourceBundle orderingComponents = java.util.ResourceBundle.getBundle(ORDER_COMPONENTS);

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
    private List<Window> myWindows;
    private WindowFactory myWindowCreator;

    public LogoVisualization(ParserController control)
    {
        //myStage = stage;
        myController = control;
        parameters = new ArrayList<>();
        myWindows = new ArrayList<>();
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
        myWindowCreator = new WindowFactory(parameters);

        myConsole = new ConsoleWindow(myController,updateNeeded,myCode);
        graphics = new TurtleWindow(toolbar.getActiveBackgroundColor(),toolbar.getActiveTurtleImage(),myController,toolbar.getActivePenColor());


        myWindows.add(myConsole);
        myWindows.add(graphics);

        VBox leftComps = new VBox();
        VBox rightComps = new VBox();

        fillWindows(LEFT_COMPONENTS,leftComps);
        fillWindows(RIGHT_COMPONENTS,rightComps);



//        myVariables = new VariableWindow(myController,updateNeeded,myCode);
//        myHistory = new HistoryWindow(myController,updateNeeded,myCode);
//        available = new AvailableCommandsWindow(toolbar.getActiveLanguage(),myController,updateNeeded,myCode);
//        turtleMover = new MoveTurtleWindow(myController,updateNeeded,myCode);
//        myPalette = new PaletteWindow(myController,updateNeeded,myCode);
//        myTurtleInfo = new TurtleCompleteInfoWindow(myController,updateNeeded,myCode);
//        leftComps.getChildren().addAll(myHistory.getView(),available.getView(),myVariables.getView());
//        rightComps.getChildren().addAll(turtleMover.getView(),myPalette.getView(),myTurtleInfo.getView());




        HBox bottom  = new HBox();
        bottom.getChildren().addAll(myConsole.getView());
        bottom.setMaxHeight(50);



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

    private void fillWindows(String side, VBox container)
    {
        for(String windowName: orderingComponents.getString(side).split(","))
        {
            Window comp = myWindowCreator.makeWindow(windowName);
            myWindows.add(comp);
            container.getChildren().add(comp.getView());
        }
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
            myConsole.addReturn(myController.parseCode(myCode.getCodeToBeParsed()));
        }
        catch (Exception e)
        {
            showError(e.getMessage());
        }

        for(Window w:myWindows)
        {
            w.update();
        }

        toolbar.update();

//        graphics.update();
//        myHistory.update();
//        myVariables.update();
//        myPalette.update();
//        myTurtleInfo.update();
//        toolbar.update();


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
