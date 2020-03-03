package slogo.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import slogo.controller.ParserController;


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
    private ParserController myController;
    private ColorPalette myColorPalette;
    private TitledPane colorPaletteView;
    private ListView colorMatcher;
    private ListView imageMatcher;
    private TitledPane imagePaletteView;
    private SimpleBooleanProperty tellUpdate;
    private HBox paletteContainer;

    public Palette(ParserController control, SimpleBooleanProperty update)
    {
        tellUpdate = update;
        myController = control;
        myColorPalette = myController.getColorPalette();

        myView = new TitledPane();
        paletteContainer = new HBox();

        colorPaletteView = new TitledPane();
        colorMatcher = new ListView<String>();
        colorMatcher.setCellFactory(listView -> new PaletteColorCell(myColorPalette));

        fillPenColorsPalette();

        imagePaletteView = new TitledPane();
        imageMatcher = new ListView<String>();
        imageMatcher.setCellFactory(listView -> new PaletteImagecell());

        fillImagesPalette();

        paletteContainer.getChildren().addAll(colorPaletteView, imagePaletteView);
        myView.setText("Palette");
        myView.setContent(paletteContainer);
        myView.setMaxWidth(50);

    }

    private void fillPenColorsPalette()
    {
        colorMatcher.getItems().clear();
        colorPaletteView.setText(visualText.getString(PENCOLORS));
        for(int index: myColorPalette.getAvailableIndices())
        {

            colorMatcher.getItems().add(index+"");

        }

        colorPaletteView.setContent(colorMatcher);


    }

    private void fillImagesPalette()
    {
        imageMatcher.getItems().clear();
        imagePaletteView.setText(visualText.getString(IMAGES));
        for(String image: turtleImages.keySet())
        {
            imageMatcher.getItems().add(image);
        }
        imagePaletteView.setContent(imageMatcher);
    }

    public Node getView()
    {
        return myView;
    }

    public void update()
    {
        fillPenColorsPalette();
    }

}
