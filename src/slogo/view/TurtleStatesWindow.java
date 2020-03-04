package slogo.view;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.util.ResourceBundle;


public class TurtleStatesWindow extends Window{
    private static final String UI_TEXT = "resources.UIText";
    private static final String CSS_ID = "TurtleStates";

    private VBox myView;
    private Label turtleID;
    private Label turtlePosition;
    private Label turtleHeading;
    private ViewTurtle myViewTurtle;

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    public TurtleStatesWindow(ViewTurtle view)
    {
        myView = new VBox();
        myView.setId(CSS_ID);
        myView.setMinWidth(110);
        myView.setMaxWidth(110);
        myViewTurtle = view;
        update();
        myView.getChildren().addAll(turtleID,turtlePosition,turtleHeading);
    }
    private Label makeLabel(String identifier, String info)
    {
        return new Label(identifier + ":" + info);
    }
    @Override
    public void update() {
        turtleID = makeLabel(visualText.getString("turtleid"),myViewTurtle.getID()+"");
        turtlePosition = makeLabel(visualText.getString("turtleposition"),myViewTurtle.getCoordinates().toString());
        turtlePosition.setStyle("-fx-font-size: 11");
        turtleHeading = makeLabel(visualText.getString("turtleheading"),myViewTurtle.getHeading() + "");
    }

    @Override
    public Node getView() {
        return myView;
    }


}
