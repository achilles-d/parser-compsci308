package slogo.view.windows;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import slogo.controller.Controller;
import slogo.view.components.CodeStage;
import slogo.view.components.ColorPalette;
import slogo.view.components.PaletteColorCell;
import slogo.view.components.PaletteImagecell;


import java.util.ResourceBundle;

public class PaletteWindow extends Window {

    private static final String UI_TEXT = "resources.UIText";
    private static final String AVAILABLE_COLORS = "availablecolors";
    private static final String IMAGES = "images";
    private static final int MAX_WIDTH = 50;
    private static final String PALETTE = "palette";


    private ResourceBundle turtleImages;
    private ResourceBundle visualText = java.util.ResourceBundle.getBundle(UI_TEXT);


    private TitledPane myView;
    private ColorPalette myColorPalette;
    private TitledPane colorPaletteView;
    private ListView colorMatcher;
    private ListView imageMatcher;
    private TitledPane imagePaletteView;
    private HBox paletteContainer;

    public PaletteWindow(Controller control, SimpleBooleanProperty update, CodeStage code)
    {
        tellUpdate = update;
        myController = control;
        myColorPalette = myController.getColorPalette();
        turtleImages = ResourceBundle.getBundle(myController.getAvailableImagesFile());

        myView = new TitledPane();
        paletteContainer = new HBox();

        colorPaletteView = new TitledPane();
        colorMatcher = new ListView<String>();
        colorMatcher.setCellFactory(listView -> new PaletteColorCell(myColorPalette));

        fillPenColorsPalette();

        imagePaletteView = new TitledPane();
        imageMatcher = new ListView<String>();
        imageMatcher.setCellFactory(listView -> new PaletteImagecell(myController.getAvailableImagesFile()));

        fillImagesPalette();

        paletteContainer.getChildren().addAll(colorPaletteView, imagePaletteView);
        myView.setText(visualText.getString(PALETTE));
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
