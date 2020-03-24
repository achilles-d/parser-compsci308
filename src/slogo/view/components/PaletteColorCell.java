package slogo.view.components;

import javafx.scene.control.ListCell;
import javafx.scene.shape.Rectangle;
import slogo.view.components.ColorPalette;

import java.util.ResourceBundle;

/**
 * @author Saurav Sanjay
 * Extension of list cell used for displaying color palette as a list with a color index next to a color square
 * This short class represents good design as it serves a single purpose of rendering a list cell that will
 * display a color next to an index as intended. Making a separate class for this instead of trying to hardcode
 * it into a default ListCell makes it so that this kind of cell can be used in other parts as well.
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
