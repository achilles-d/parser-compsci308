package slogo.view.windows;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import slogo.view.components.ViewTurtle;

import java.util.ResourceBundle;


/**
 * @author Saurav Sanjay
 * Window pane with information about turtle, including ID, heading, and position
 */

public class TurtleStatesWindow extends Window {
    private static final String UI_TEXT = "resources.UIText";
    private static final String CSS_ID = "TurtleStates";
    private static final int WIDTH = 110;
    private static final String TURTLEID = "turtleid";
    private static final String TURTLEPOSITION = "turtleposition";
    private static final String TURTLEHEADING = "turtleheading";

    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);

    private VBox myView;
    private Label turtleID;
    private Label turtlePosition;
    private Label turtleHeading;
    private ViewTurtle myViewTurtle;


    /**
     * Creates a new TurtleStatesWindow
     * @param view ViewTurtle to display information for
     */
    public TurtleStatesWindow(ViewTurtle view)
    {
        myView = new VBox();
        myView.setId(CSS_ID);
        myView.setMinWidth(WIDTH);
        myView.setMaxWidth(WIDTH);
        myViewTurtle = view;
        update();
        myView.getChildren().addAll(turtleID,turtlePosition,turtleHeading);
    }
    private Label makeLabel(String identifier, String info)
    {
        return new Label(identifier + ":" + info);
    }

    /**
     * Updates view, in this case, updates turtle information when turtle changes position or heading
     */
    @Override
    public void update() {
        turtleID = makeLabel(visualText.getString(TURTLEID),myViewTurtle.getID()+"");
        turtlePosition = makeLabel(visualText.getString(TURTLEPOSITION),myViewTurtle.getCoordinates().toString());
        turtlePosition.setStyle("-fx-font-size: 11");
        turtleHeading = makeLabel(visualText.getString(TURTLEHEADING),myViewTurtle.getHeading() + "");
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
