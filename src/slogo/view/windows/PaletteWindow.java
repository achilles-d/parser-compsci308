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

/**
 * @author Saurav Sanjay
 * Window pane for image and color palette to be used by user for reference when coding
 * This reprsents some good design as it extends from the Window class to create a specific view.
 * Extending from Window allows me to treat it like any other Window object in the LogoVisualization and just update()
 * it when needed, regardless of its implementation. It also makes use of short helper methods, and also ensures
 * that all text that is visible comes from a properties file.
 */
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

    /**
     * Creates a new PaletteWindow object
     * @param control controller to be used
     * @param update boolean property that will indicate when the view needs to be updated
     * @param code a CodeStage object that holds code that needs to be parsed
     */
    public PaletteWindow(Controller control, SimpleBooleanProperty update, CodeStage code)
    {
        tellUpdate = update;
        myController = control;
        myCode = code;

        myColorPalette = myController.getColorPalette();
        turtleImages = ResourceBundle.getBundle(myController.getAvailableImagesFile());

        myView = new TitledPane();
        paletteContainer = new HBox();

        makeColorsPaletteView();
        makeImagesPaletteView();

        paletteContainer.getChildren().addAll(colorPaletteView, imagePaletteView);
        myView.setText(visualText.getString(PALETTE));
        myView.setContent(paletteContainer);
        myView.setMaxWidth(MAX_WIDTH);

    }

    private void makeImagesPaletteView() {
        imagePaletteView = new TitledPane();
        imageMatcher = new ListView<String>();
        imageMatcher.setCellFactory(listView -> new PaletteImagecell(myController.getAvailableImagesFile()));

        fillImagesPalette();
    }

    private void makeColorsPaletteView() {
        colorPaletteView = new TitledPane();
        colorMatcher = new ListView<String>();
        colorMatcher.setCellFactory(listView -> new PaletteColorCell(myColorPalette));

        fillPenColorsPalette();
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

    /**
     * Returns Node object for display
     * @return Node for display
     */
    public Node getView()
    {
        return myView;
    }

    /**
     * Updates view, in this case, updates palette in case user edits indices for colors
     */
    public void update()
    {
        fillPenColorsPalette();
    }

}
