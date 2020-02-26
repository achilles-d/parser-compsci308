package slogo.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import slogo.controller.ParserController;
import slogo.model.Coordinate;
import slogo.model.Line;

public class TurtleWindow extends Window {

    private static final int MAX_WIDTH = 750;
    private static final int MAX_HEIGHT = 573;
    private Pane myView;
    private Pane canvasWrap;
    private Canvas background;
    private ViewTurtle myTurtle;
    private SimpleStringProperty backgroundColor;
    private SimpleStringProperty penColor;
    private GraphicsContext drawer;
    private ParserController myController;
    private static final int X_LAYOUT_SCALING = 375;
    private static final double Y_LAYOUT_SCALING = 286.5;


    public TurtleWindow(Property menuBackgroundColor, Property turtleImage, ParserController control,Property menuPenColor)
    {
        myController = control;
        myView = new Pane();
        myView.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
        //myView.setMinSize(0,0);
        background = new Canvas();
        drawer = background.getGraphicsContext2D();
        myTurtle = new ViewTurtle(turtleImage);
        canvasWrap = new Pane();
        //canvasWrap.setMaxSize(0,0);
        canvasWrap.setPrefSize(MAX_WIDTH, MAX_HEIGHT);
        canvasWrap.setMaxSize(MAX_WIDTH, MAX_HEIGHT);
        canvasWrap.getChildren().addAll(background,myTurtle.getView());
        background.widthProperty().bind(canvasWrap.widthProperty());
        background.heightProperty().bind(canvasWrap.heightProperty());
        //background.widthProperty().bind(myView.widthProperty());
       // background.heightProperty().bind(myView.heightProperty());
        myView.getChildren().addAll(canvasWrap);

        backgroundColor = new SimpleStringProperty();
        //backgroundColor.bind(menuBackgroundColor);
        backgroundColor = (SimpleStringProperty)menuBackgroundColor;
        backgroundColor.addListener((observable, oldValue, newValue) -> {setBackgroundColor(backgroundColor.getValue());});

        penColor = (SimpleStringProperty)menuPenColor;
        penColor.addListener((observable, oldValue, newValue) -> setPenColor(newValue));


        setBackgroundColor(backgroundColor.getValue());
        //testDrawLine();
       // myView.setStyle("-fx-background-color: red");
       // System.out.println(myTurtle.getView().getLayoutX());
        //System.out.println(myTurtle.getView().getTranslateX());

       // myTurtle.updatePosition(new Coordinate(-391,260.5));

    }

    private void setPenColor(String color)
    {
        penColor.setValue(color);
    }

    public void getSize()
    {
        System.out.println(myView.getHeight());
        System.out.println(myView.getWidth());
    }

    public void setBackgroundColor(String color) {
        myView.setBackground(new Background(new BackgroundFill(Color.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY)));
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

    private void drawLines()
    {
        if(myController.getLines().size() ==0)
            clearScreen();
        else
        {
            drawer.setStroke(Color.valueOf(penColor.getValue()));
            for(Line l: myController.getLines())
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


        myTurtle.updatePosition(myController.getTurtlePosition());
        myTurtle.setHeading(myController.getHeading());
        drawLines();
    }



    @Override
    public Pane getView() {
        return myView;
    }
}
