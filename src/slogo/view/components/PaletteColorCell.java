package slogo.view.components;

import javafx.scene.control.ListCell;
import javafx.scene.shape.Rectangle;
import slogo.view.components.ColorPalette;

import java.util.ResourceBundle;

//https://stackoverflow.com/questions/25570803/image-in-javafx-listview

public class PaletteColorCell extends ListCell<String> {

    private static final String PEN_COLOR = "resources.colors.PenColor";
    private static final int PATCH_SIZE = 20;

    private ResourceBundle penColorsNames = java.util.ResourceBundle.getBundle(PEN_COLOR);
    private ColorPalette myPalette;

    public PaletteColorCell(ColorPalette palette)
    {
        super();
        myPalette = palette;
    }
    @Override
    protected void updateItem(String colorIndex,boolean empty)
    {
        super.updateItem(colorIndex,empty);
        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            Rectangle colorSplatch = new Rectangle(PATCH_SIZE, PATCH_SIZE, myPalette.getColor(Integer.valueOf(colorIndex)));
            setText(colorIndex);
            setGraphic(colorSplatch);
        }
    }


}
