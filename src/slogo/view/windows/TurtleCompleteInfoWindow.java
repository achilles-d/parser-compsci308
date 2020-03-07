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

    @Override
    public void update() {
        turtleStates.getChildren().clear();

        for(ViewTurtle view: myTurtleController.getAllActiveViewTurtles())
        {
            turtleStates.getChildren().add(makeNewTurtleStatesWindow(view));
        }

    }

    @Override
    public Node getView() {
        return myView;
    }
}
