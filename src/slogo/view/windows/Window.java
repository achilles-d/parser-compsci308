package slogo.view.windows;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import slogo.controller.Controller;
import slogo.view.components.CodeStage;

public abstract class Window {

    protected Controller myController;
    protected SimpleBooleanProperty tellUpdate;
    protected CodeStage myCode;


    public abstract void update();

    public abstract Node getView();
}
