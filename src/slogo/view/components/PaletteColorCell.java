package slogo.view.components;

import javafx.scene.control.ListCell;
import javafx.scene.shape.Rectangle;
import slogo.view.components.ColorPalette;

import java.util.ResourceBundle;

/**
 * @author Saurav Sanjay
 * Extension of list cell used for displaying color palette as a list with a color index next to a color square
 */


public class PaletteColorCell extends ListCell<String> {

    private static final int PATCH_SIZE = 20;

    private ColorPalette myPalette;

    /**
     * Creates a new PaletteColorCell with given ColorPalette
     * @param palette ColorPalette for this workspace
     */
    public PaletteColorCell(ColorPalette palette)
    {
        super();
        myPalette = palette;
    }

    /**
     * renders list cell with a text for index and a graphic with a color splatch that indicates to user color and index associated with palette
     * @param colorIndex index of colorin palette
     * @param empty indicates whether list cell is empty or not
     */
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
