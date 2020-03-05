package slogo.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.controller.ParserController;
import slogo.controller.TurtleController;

import java.util.ResourceBundle;

public class TurtleCompleteInfoWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final int SIZE_COMPONENT = HEIGHT;
    private static final String TURTLEINFO = "turtleinfo";
    private static final int SPACING = 15;
    private static final int HEIGHT = 400;

    private TitledPane myView;
    private VBox turtleStates;
    private ParserController myController;
    private TurtleController myTurtleController;
    private ColorPalette myColorPalette;
    private ScrollPane myContainer;

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);


    public TurtleCompleteInfoWindow(ParserController control, SimpleBooleanProperty update, CodeStage code)
    {
        myController = control;
        myTurtleController = control.getTurtleController();
        myColorPalette = control.getColorPalette();
        turtleStates = new VBox();
        myContainer = new ScrollPane();

        myView = new TitledPane();
        myView.setText(visualText.getString(TURTLEINFO));
        update();
        myContainer.setContent(turtleStates);
        turtleStates.setPrefHeight(HEIGHT);
        turtleStates.setMinHeight(HEIGHT);
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
