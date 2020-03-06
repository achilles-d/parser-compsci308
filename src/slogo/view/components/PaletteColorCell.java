package slogo.view.components;

import javafx.scene.control.ListCell;
import javafx.scene.shape.Rectangle;
import slogo.view.components.ColorPalette;

import java.util.ResourceBundle;



public class PaletteColorCell extends ListCell<String> {

    private static final int PATCH_SIZE = 20;

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
