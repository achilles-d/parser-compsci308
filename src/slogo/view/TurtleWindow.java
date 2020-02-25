package slogo.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import slogo.model.Coordinate;

public class TurtleWindow extends Window {

    private Pane myView;
    private Pane canvasWrap;
    private Canvas background;
    private ViewTurtle myTurtle;
    private SimpleStringProperty backgroundColor;
    private SimpleStringProperty penColor;
    private GraphicsContext drawer;

    public TurtleWindow(Property menuBackgroundColor, Property turtleImage)
    {
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

        penColor = new SimpleStringProperty();

        setBackgroundColor(backgroundColor.getValue());
        //testDrawLine();
       // myView.setStyle("-fx-background-color: red");
       // System.out.println(myTurtle.getView().getLayoutX());
        //System.out.println(myTurtle.getView().getTranslateX());

        myTurtle.updatePosition(-350,260);

    }

    public void getSize()
    {
        System.out.println(myView.getHeight());
        System.out.println(myView.getWidth());
    }

    public void setBackgroundColor(String color) {
        myView.setBackground(new Background(new BackgroundFill(Color.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY)));
    }




    private void testDrawLine()
    {
        drawer.setStroke(Color.valueOf(penColor.getValue()));
        drawer.strokeLine(0,0,100,100);
        drawer.strokeLine(0,0,100,0);


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

    }

    @Override
    public Pane getView() {
        return myView;
    }
}
