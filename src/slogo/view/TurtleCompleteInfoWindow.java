package slogo.view;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import slogo.controller.ParserController;
import slogo.controller.TurtleController;

import java.util.ResourceBundle;

public class TurtleCompleteInfoWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final int SIZE_COMPONENT = 400;

    private TitledPane myView;
    private VBox turtleStates;
    private ParserController myController;
    private TurtleController myTurtleController;
    private ColorPalette myColorPalette;

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);


    public TurtleCompleteInfoWindow(ParserController control)
    {
        myController = control;
        myTurtleController = control.getTurtleController();
        myColorPalette = control.getColorPalette();
        turtleStates = new VBox();
        myView = new TitledPane();
        myView.setText(visualText.getString("turtleinfo"));
        update();
        myView.setContent(turtleStates);
        myView.setMaxWidth(SIZE_COMPONENT);

    }


    private Node makeNewTurtleStatesWindow(ViewTurtle viewTurt)
    {
        HBox info = new HBox();
        info.getChildren().add(new TurtleStatesWindow(viewTurt).getView());
        info.getChildren().add(new PenStateWindow(viewTurt,myColorPalette).getView());
        info.setSpacing(15);
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
