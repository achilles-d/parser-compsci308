package slogo.view.components;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;

/**
 * @author Saurav Sanjay
 * Extension of List cell used to render a view of image palette with image index next to the actual image
 */
public class PaletteImagecell extends ListCell<String> {

    private static final int PATCH_SIZE = 50;

    private ResourceBundle turtleImages;
    private ImageView imageIcon = new ImageView();

    /**
     * Creates a new cell for image palette
     * @param imagePalette name of property file associated with image palette file
     */
    public PaletteImagecell(String imagePalette)
    {
        turtleImages = ResourceBundle.getBundle(imagePalette);
    }

    /**
     * Renders a list cell with image index next to image graphic
     * @param imageIndex index associated with image in palette
     * @param empty indicates whether list cell is empty
     */
    @Override
    protected void updateItem(String imageIndex,boolean empty)
    {
        super.updateItem(imageIndex,empty);
        if(empty)
        {
            setText(null);
            setGraphic(null);
        }
        else
        {
            String imgName = turtleImages.getString(imageIndex).replaceAll("\"","");
            imageIcon.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(imgName)));
            imageIcon.setFitWidth(PATCH_SIZE);
            imageIcon.setFitHeight(PATCH_SIZE);
            setText(imageIndex);
            setGraphic(imageIcon);
        }
    }


}
