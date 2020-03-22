package slogo.view.windows;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import slogo.controller.Controller;
import slogo.controller.TurtleController;
import slogo.view.components.ColorPalette;
import slogo.view.components.ViewTurtle;
import slogo.model.turtle.Line;

/**
 * @author Saurav Sanjay
 * Window pane for graphics of turtle moving around screen
 */
public class TurtleWindow extends Window {

    private static final int MAX_WIDTH = 750;
    private static final int MAX_HEIGHT = 573;
    private static final int X_LAYOUT_SCALING = 375;
    private static final double Y_LAYOUT_SCALING = 286.5;

    private Pane myView;
    private Pane canvasWrap;
    private Canvas background;
    private TurtleController myTurtleController;
    private SimpleDoubleProperty backgroundColor;
    private SimpleDoubleProperty penColor;
    private GraphicsContext drawer;
    private SimpleStringProperty turtleImage;
    private ColorPalette myColorPalette;


    /**
     * Creates a new TurtleWindow object
     * @param menuBackgroundColor background color for this graphics screen
     * @param turtleImg image name for ViewTurtle
     * @param control Controller for this workspace
     * @param menuPenColor pen color to be used for graphics
     */
    public TurtleWindow(Property menuBackgroundColor, Property turtleImg, Controller control,Property menuPenColor)
    {
        myController = control;
        myColorPalette = control.getColorPalette();
        myTurtleController = control.getTurtleController();

        myView = new Pane();
        myView.setMaxSize(MAX_WIDTH, MAX_HEIGHT);

        turtleImage = (SimpleStringProperty) turtleImg;

        makeCanvas();
        fillCanvas();

        background.widthProperty().bind(canvasWrap.widthProperty());
        background.heightProperty().bind(canvasWrap.heightProperty());

        myView.getChildren().addAll(canvasWrap);

        makeListeners(menuBackgroundColor,menuPenColor);

        setBackgroundColor((int)(backgroundColor.get()));

    }

    private void makeListeners(Property menuBackgroundColor,Property menuPenColor)
    {
        backgroundColor = (SimpleDoubleProperty)menuBackgroundColor;
        backgroundColor.addListener((observable, oldValue, newValue) -> {setBackgroundColor((int)backgroundColor.get());});

        penColor = (SimpleDoubleProperty)menuPenColor;
        penColor.addListener((observable, oldValue, newValue) -> setPenColor((int)penColor.get()));
    }

    private void makeCanvas()
    {
        background = new Canvas();
        drawer = background.getGraphicsContext2D();

        canvasWrap = new Pane();
        canvasWrap.setMinSize(MAX_WIDTH, MAX_HEIGHT);
        canvasWrap.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        canvasWrap.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
    }

    private void fillCanvas()
    {
        canvasWrap.getChildren().clear();
        canvasWrap.getChildren().add(background);
        for(ViewTurtle turtle:myTurtleController.getAllViewTurtles()) {
            canvasWrap.getChildren().add(turtle.getView());
            turtle.setImageProperty(turtleImage);
        }

    }

    private void setPenColor(int color)
    {
        for(ViewTurtle view:myTurtleController.getAllActiveViewTurtles())
        {
            view.setPenColorIndex(color);
        }
    }


    /**
     * Sets background color for graphics screen
     * @param color index of desired color in color palette
     */
    public void setBackgroundColor(int color) {
        myView.setBackground(new Background(new BackgroundFill(myColorPalette.getColor(color), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private double adjustX(double x)
    {
        return x + X_LAYOUT_SCALING;
    }

    private double adjustY(double y)
    {
        return -y + Y_LAYOUT_SCALING;
    }

    private void clearScreen()
    {
        drawer.clearRect(0,0, background.getWidth(),background.getHeight());
    }

    private void drawLines(int turtleID)
    {
        ViewTurtle active = myTurtleController.getViewTurtle(turtleID);
        if(myTurtleController.getLines(turtleID).size() ==0)
        {

            clearScreen();
        }
        else
        {
            drawer.setStroke(myColorPalette.getColor((int)active.getPenColorIndex()));
            drawer.setLineWidth(active.getPenSizeProperty().get());

            for(Line l: myTurtleController.getLines(turtleID))
            {
                if(!l.isDrawn())
                {
                    double startX = adjustX(l.getStart().getXVal());
                    double startY = adjustY(l.getStart().getYVal());
                    double endX = adjustX(l.getEnd().getXVal());
                    double endY = adjustY(l.getEnd().getYVal());
                    drawer.strokeLine(startX,startY,endX,endY);
                    l.drewLine();
                }

            }

        }



    }

    /**
     * Updates view, in this case, moves turtle, draws or clears lines
     */
    @Override
    public void update() {

        fillCanvas();
        for(ViewTurtle view: myTurtleController.getAllActiveViewTurtles())
        {
            view.updatePosition(myTurtleController.getTurtlePosition(view.getID()));
            view.setHeading(myTurtleController.getHeading(view.getID()));
            view.setVisibility(myTurtleController.getTurtleVisibility(view.getID()));
            drawLines(view.getID());
        }

    }



    /**
     * Returns Node object for display
     * @return Node for display
     */
    @Override
    public Node getView() {
        return myView;
    }
}
