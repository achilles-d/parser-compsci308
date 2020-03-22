package slogo.view.windows;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import slogo.controller.Controller;
import slogo.view.components.CodeStage;

/**
 * @author Saurav Sanjay
 * Abstract window class to be extended upon by all window pane components for workspace
 */
public abstract class Window {

    protected Controller myController;
    protected SimpleBooleanProperty tellUpdate;
    protected CodeStage myCode;


    /**
     * Updates view of window pane component
     */
    public abstract void update();

    /**
     * Returns Node object to be dislayed
     * @return Node object for display
     */
    public abstract Node getView();
}
