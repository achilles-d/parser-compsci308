package slogo.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;


import java.util.ResourceBundle;

public class Palette {

    private static final String DEFAULT_PEN_COLOR = "Black";
    private static final String PEN_COLOR = "resources.colors.PenColor";
    private static final String UI_TEXT = "resources.UIText";
    private static final String TURTLE_IMAGES = "resources.TurtleImage";
    private static final String PENCOLORS = "pencolors";
    private static final String IMAGES = "images";


    private ResourceBundle penColorsNames = java.util.ResourceBundle.getBundle(PEN_COLOR);
    private ResourceBundle turtleImages = java.util.ResourceBundle.getBundle(TURTLE_IMAGES);
    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);




    private TitledPane myView;
    private TitledPane colorPalette;
    private ListView colorMatcher;
    private ListView imageMatcher;
    private TitledPane imagePalette;
    private HBox paletteContainer;

    //https://stackoverflow.com/questions/25570803/image-in-javafx-listview
    public Palette()
    {
        myView = new TitledPane();
        paletteContainer = new HBox();

        colorPalette = new TitledPane();
        colorMatcher = new ListView<String>();
        colorMatcher.setCellFactory(listView -> new PaletteColorCell());

        makePenColorsPalette();

        imagePalette = new TitledPane();
        imageMatcher = new ListView<String>();
        makeImagesPalette();

        paletteContainer.getChildren().addAll(colorPalette,imagePalette);
        myView.setText("Palette");
        myView.setContent(paletteContainer);
        myView.setMaxWidth(50);

    }

    private void makePenColorsPalette()
    {
        colorPalette.setText(visualText.getString(PENCOLORS));
        for(String color: penColorsNames.keySet())
        {

            colorMatcher.getItems().add(color);

        }

        colorPalette.setContent(colorMatcher);


    }

    private void makeImagesPalette()
    {
        imagePalette.setText(visualText.getString(IMAGES));
        for(String image: turtleImages.keySet())
        {
            imageMatcher.getItems().add(image + " " + turtleImages.getString(image));
        }
        imagePalette.setContent(imageMatcher);
    }

    public Node getView()
    {
        return myView;
    }

}
