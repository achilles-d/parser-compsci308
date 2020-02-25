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


    public TurtleWindow(Property menuBackgroundColor, Property turtleImage, ParserController control)
    {
        myController = control;
        myView = new Pane();
        myView.setMaxSize(750,573);
        //myView.setMinSize(0,0);
        background = new Canvas();
        drawer = background.getGraphicsContext2D();
        myTurtle = new ViewTurtle(turtleImage);
        canvasWrap = new Pane();
        //canvasWrap.setMaxSize(0,0);
        canvasWrap.setMaxSize(750,573);
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

        penColor = new SimpleStringProperty("Black");

        setBackgroundColor(backgroundColor.getValue());
        //testDrawLine();
       // myView.setStyle("-fx-background-color: red");
       // System.out.println(myTurtle.getView().getLayoutX());
        //System.out.println(myTurtle.getView().getTranslateX());

       // myTurtle.updatePosition(new Coordinate(-391,260.5));

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
        return x + X_LAYOUT_SCALING - myTurtle.getSize()/2;
    }

    private double adjustY(double y)
    {
        return -y + Y_LAYOUT_SCALING - myTurtle.getSize()/2;
    }

    private void drawLines()
    {
        System.out.println(myController.getLines().size());
        drawer.clearRect(0,0, background.getWidth(),background.getHeight());
        drawer.setStroke(Color.valueOf(penColor.getValue()));

        for(Line l: myController.getLines())
        {
            double startX = adjustX(l.getStart().getXVal());
            double startY = adjustY(l.getStart().getYVal());
            double endX = adjustX(l.getEnd().getXVal());
            double endY = adjustY(l.getEnd().getYVal());
            System.out.println(startX + "," + startY + "  " + endX + "," + endY);
            drawer.strokeLine(startX,startY,endX,endY);
        }



        /*
        Line testLine = new Line();
        testLine.setStartX(500);
        testLine.setEndX(500);
        testLine.setStartY(100);
        testLine.setStartY(400);
        //Line testLine3 = new Line(0,0,100,0);
        //Line testLine2 = new Line(100,100,400,100);
        myView.getChildren().addAll(testLine);
        */

    }

    @Override
    public void update() {
        /*
        System.out.println("tried to update");
        System.out.println(myController.getTurtlePosition());
         */

        myTurtle.updatePosition(myController.getTurtlePosition());
        drawLines();
    }



    @Override
    public Pane getView() {
        return myView;
    }
}
