package slogo.view.windows;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.controller.Controller;
import slogo.controller.TurtleController;
import slogo.view.components.CodeStage;
import slogo.view.components.ColorPalette;
import slogo.view.components.ViewTurtle;

import java.util.ResourceBundle;

/**
 * @author Saurav Sanjay
 * Window pane with all information for all active turtles, including pen info, position and heading
 */
public class TurtleCompleteInfoWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final int SIZE_COMPONENT = 400;
    private static final String TURTLEINFO = "turtleinfo";
    private static final int SPACING = 15;

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);


    private TitledPane myView;
    private VBox turtleStates;
    private TurtleController myTurtleController;
    private ColorPalette myColorPalette;
    private ScrollPane myContainer;


    /**
     * Creates a new TurtleCompleteInfo window
     * @param control controller to be used
     * @param update boolean property that will indicate when the view needs to be updated
     * @param code a CodeStage object that holds code that needs to be parsed
     */
    public TurtleCompleteInfoWindow(Controller control, SimpleBooleanProperty update, CodeStage code)
    {
        myController = control;
        myCode = code;
        tellUpdate = update;

        myTurtleController = control.getTurtleController();
        myColorPalette = control.getColorPalette();

        turtleStates = new VBox();
        myContainer = new ScrollPane();

        myView = new TitledPane();
        myView.setText(visualText.getString(TURTLEINFO));
        update();

        myContainer.setContent(turtleStates);
        turtleStates.setPrefHeight(SIZE_COMPONENT);
        turtleStates.setMinHeight(SIZE_COMPONENT);

        myView.setContent(myContainer);
        myView.setMaxWidth(SIZE_COMPONENT);

    }


    private Node makeNewTurtleStatesWindow(ViewTurtle viewTurt)
    {
        HBox info = new HBox();
        info.getChildren().add(new TurtleStatesWindow(viewTurt).getView());
        info.getChildren().add(new PenStateWindow(viewTurt,myColorPalette).getView());
        info.setSpacing(SPACING);
        return info;
    }

    /**
     * Updates view, in this case, adds a new slot for turtle info when a new turtle has been created or made active
     */
    @Override
    public void update() {
        turtleStates.getChildren().clear();

        for(ViewTurtle view: myTurtleController.getAllActiveViewTurtles())
        {
            turtleStates.getChildren().add(makeNewTurtleStatesWindow(view));
        }

    }

    /**
     * Returns Node object for display
     * @return Node for display
     */
    @Override
    public Node getView() {
        return myView;
    }
}
