package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.controller.ParserController;
import slogo.controller.TurtleController;

public class TurtleCompleteInfoWindow {

    private TitledPane myView;
    private VBox turtleStates;
    private ParserController myController;
    private TurtleController myTurtleController;
    private ColorPalette myColorPalette;

    public TurtleCompleteInfoWindow(ParserController control, ColorPalette colors)
    {
        myController = control;
        myTurtleController = control.getTurtleController();
        myColorPalette = colors;
        init();
    }

    private void init()
    {

    }

    private Node makeNewTurtleStatesWindow(int id)
    {
        HBox info = new HBox();
        ViewTurtle active = myTurtleController.getViewTurtle(id);
        info.getChildren().add(new TurtleStatesWindow(active).getView());
        info.getChildren().add(new PenStateWindow(active,myColorPalette).getView());
        return info;
    }

    @Override
    public void update() {

    }

    @Override
    public Node getView() {
        return null;
    }
}
