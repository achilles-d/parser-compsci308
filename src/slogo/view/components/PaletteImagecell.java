package slogo.view.components;

import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ResourceBundle;


public class PaletteImagecell extends ListCell<String> {

    private static final int PATCH_SIZE = 50;

    private ResourceBundle turtleImages;
    private ImageView imageIcon = new ImageView();

    public PaletteImagecell(String imagePalette)
    {
        turtleImages = ResourceBundle.getBundle(imagePalette);
    }

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
