package slogo.view;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import slogo.controller.ParserController;


import java.util.ResourceBundle;

public class PaletteWindow extends Window{

    private static final String DEFAULT_PEN_COLOR = "Black";
    private static final String PEN_COLOR = "resources.colors.PenColor";
    private static final String UI_TEXT = "resources.UIText";
    private static final String TURTLE_IMAGES = "resources.TurtleImage";
    private static final String AVAILABLE_COLORS = "availablecolors";
    private static final String IMAGES = "images";
    private static final int MAX_WIDTH = 50;


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

    public PaletteWindow(ParserController control, SimpleBooleanProperty update, CodeStage code)
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
        myView.setMaxWidth(MAX_WIDTH);

    }

    private void fillPenColorsPalette()
    {
        colorMatcher.getItems().clear();
        colorPaletteView.setText(visualText.getString(AVAILABLE_COLORS));
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
