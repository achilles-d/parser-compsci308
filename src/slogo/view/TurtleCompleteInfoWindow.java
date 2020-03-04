package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import slogo.controller.ParserController;

public class TurtleCompleteInfoWindow {

    private TitledPane myView;
    private ParserController myController;
    private ColorPalette myColorPalette;

    public TurtleCompleteInfoWindow(ParserController control, ColorPalette colors)
    {
        myController = control;
        myColorPalette = colors;
        init();
    }

    private void init()
    {

    }

    @Override
    public void update() {

    }

    @Override
    public Node getView() {
        return null;
    }
}
