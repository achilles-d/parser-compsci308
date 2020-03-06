package slogo.view.windows;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import slogo.controller.ParserController;
import slogo.controller.TurtleController;
import slogo.model.backEndInternal.Line;
import slogo.view.components.CodeStage;
import slogo.view.components.ColorPalette;
import slogo.view.components.ViewTurtle;

public class TurtleWindow extends Window {

    private static final int MAX_WIDTH = 750;
    private static final int MAX_HEIGHT = 573;
    private Pane myView;
    private Pane canvasWrap;
    private Canvas background;
    private TurtleController myTurtleController;
    private SimpleDoubleProperty backgroundColor;
    private SimpleDoubleProperty penColor;
    private GraphicsContext drawer;
    private ParserController myController;
    private SimpleBooleanProperty tellUpdate;
    private SimpleStringProperty turtleImage;
    private CodeStage myCode;
    private ColorPalette myColorPalette;

    private static final int X_LAYOUT_SCALING = 375;
    private static final double Y_LAYOUT_SCALING = 286.5;


    public TurtleWindow(Property menuBackgroundColor, Property turtleImg, ParserController control,Property menuPenColor)
    {
        myController = control;
        myColorPalette = control.getColorPalette();
        myView = new Pane();
        myView.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
        //myView.setMinSize(0,0);
        background = new Canvas();
        drawer = background.getGraphicsContext2D();
        myTurtleController = control.getTurtleController();
        canvasWrap = new Pane();
        //canvasWrap.setMaxSize(0,0);
        canvasWrap.setMinSize(MAX_WIDTH, MAX_HEIGHT);
        canvasWrap.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        canvasWrap.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
        turtleImage = (SimpleStringProperty) turtleImg;
        fillCanvas();
        background.widthProperty().bind(canvasWrap.widthProperty());
        background.heightProperty().bind(canvasWrap.heightProperty());
        //background.widthProperty().bind(myView.widthProperty());
       // background.heightProperty().bind(myView.heightProperty());
        myView.getChildren().addAll(canvasWrap);

        //backgroundColor.bind(menuBackgroundColor);
        backgroundColor = (SimpleDoubleProperty)menuBackgroundColor;
        backgroundColor.addListener((observable, oldValue, newValue) -> {setBackgroundColor((int)backgroundColor.get());});

        penColor = (SimpleDoubleProperty)menuPenColor;
        penColor.addListener((observable, oldValue, newValue) -> setPenColor((int)penColor.get()));


        setBackgroundColor((int)(backgroundColor.get()));
        //testDrawLine();
       // myView.setStyle("-fx-background-color: red");
       // System.out.println(myTurtle.getView().getLayoutX());
        //System.out.println(myTurtle.getView().getTranslateX());

       // myTurtle.updatePosition(new Coordinate(-391,260.5));


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
            drawer.setLineWidth(active.getPenSize());
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

    @Override
    public void update() {

        fillCanvas();
        for(ViewTurtle view: myTurtleController.getAllActiveViewTurtles())
        {
            view.updatePosition(myController.getTurtlePosition());
            view.setHeading(myController.getHeading());
            view.setVisibility(myController.getTurtleVisibility());
            drawLines(view.getID());
        }

    }



    @Override
    public Pane getView() {
        return myView;
    }
}
