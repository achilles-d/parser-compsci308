package slogo.view;

import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

public class PaletteColorCell extends ListCell<String> {

    private static final String PEN_COLOR = "resources.colors.PenColor";
    private static final int PATCH_SIZE = 20;

    private ResourceBundle penColorsNames = java.util.ResourceBundle.getBundle(PEN_COLOR);


    @Override
    public void updateItem(String colorIndex,boolean empty)
    {
        super.updateItem(colorIndex,empty);
        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            Rectangle colorSplatch = new Rectangle(PATCH_SIZE, PATCH_SIZE, Color.valueOf(penColorsNames.getString(colorIndex)));
            setText(colorIndex);
            setGraphic(colorSplatch);
        }
    }


}
