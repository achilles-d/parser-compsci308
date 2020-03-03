package slogo.view;

import javafx.scene.control.ListCell;

import java.util.ResourceBundle;

public class PaletteImagecell extends ListCell<String> {

    private static final String TURTLE_IMAGES = "resources.TurtleImage";

    private ResourceBundle turtleImages = java.util.ResourceBundle.getBundle(TURTLE_IMAGES);


}
