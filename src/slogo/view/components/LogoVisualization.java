package slogo.view.components;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import slogo.controller.Controller;
import slogo.view.windows.ConsoleWindow;
import slogo.view.windows.TurtleWindow;
import slogo.view.windows.Window;
import slogo.view.windows.WindowFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LogoVisualization extends BorderPane{

    private static final String UI_TEXT = "resources.UIText";
    private static final String CSS_FILE = "/resources/uistyle.css";
    private static final String LEFT_COMPONENTS = "Left";
    private static final String RIGHT_COMPONENTS = "Right";
    private static final int MAX_BOTTOM_HEIGHT = 50;
    private static final String ORDERING_SEPARATOR = ",";
    private static final String ERROR_TITLE = "ErrorTitle";


    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);
    private ResourceBundle orderingComponents;

    private CodeStage myCode;
    private TurtleWindow graphics;
    private Controller myController;
    private SimpleBooleanProperty updateNeeded;
    private ConsoleWindow myConsole;
    private Menu toolbar;
    private List<Object> parameters;
    private List<Window> myWindows;
    private WindowFactory myWindowCreator;
    private HBox bottom;
    private VBox rightComps;
    private VBox leftComps;

    public LogoVisualization(Controller control)
    {
        myController = control;
        parameters = new ArrayList<>();
        myWindows = new ArrayList<>();
        orderingComponents = ResourceBundle.getBundle(myController.getUIOrderFile());
        init();
    }

    private void init()
    {
        myCode = new CodeStage();

        updateNeeded = new SimpleBooleanProperty();
        updateNeeded.setValue(false);
        updateNeeded.addListener(((observable, oldValue, newValue) -> checkUpdate(newValue)));

        fillParameters();

        //Need to be in same spot every workspace, so not made with a factory
        toolbar = new Menu(myController,updateNeeded,myCode);
        myConsole = new ConsoleWindow(myController,updateNeeded,myCode);
        graphics = new TurtleWindow(toolbar.getActiveBackgroundColor(),toolbar.getActiveTurtleImage(),myController,toolbar.getActivePenColor());

        myWindowCreator = new WindowFactory(parameters);

        myWindows.add(myConsole);
        myWindows.add(graphics);

        bottom  = new HBox();
        bottom.getChildren().addAll(myConsole.getView());
        bottom.setMaxHeight(MAX_BOTTOM_HEIGHT);
        bottom.setAlignment(Pos.CENTER);


        leftComps = new VBox();
        rightComps = new VBox();

        fillWindows(LEFT_COMPONENTS,leftComps);
        fillWindows(RIGHT_COMPONENTS,rightComps);

        setWindowLocations();



    }

    private void setWindowLocations()
    {
        this.setCenter(graphics.getView());
        this.setBottom(bottom);
        BorderPane.setAlignment(bottom, Pos.CENTER);
        this.setLeft(leftComps);
        this.setTop(toolbar.getView());
        this.setRight(rightComps);
    }
    private void fillWindows(String side, VBox container)
    {
        for(String windowName: orderingComponents.getString(side).split(ORDERING_SEPARATOR))
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
            if(myCode.hasCodeToBeParsed())
            {
                myController.parseCode(myCode.getCodeToBeParsed());
            }
        }
        catch (Exception e)
        {
            showError(e.getMessage());
        }

        myConsole.addReturn(myController.getReturn());
        for(Window w:myWindows)
        {
            w.update();
        }


        toolbar.update();
        myCode.clearStagedCode();
        updateNeeded.setValue(false);

    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(visualText.getString(ERROR_TITLE));
        alert.setContentText(message);
        alert.showAndWait();
    }






}
