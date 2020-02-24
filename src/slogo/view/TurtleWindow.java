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

    private StackPane myView;
    private Pane canvasWrap;
    private Canvas background;
    private ViewTurtle myTurtle;
    private SimpleStringProperty backgroundColor;

    public TurtleWindow(Property menuBackgroundColor, Property turtleImage)
    {
        myView = new StackPane();
        background = new Canvas();
        myTurtle = new ViewTurtle(turtleImage);
        canvasWrap = new Pane();
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

        setBackgroundColor(backgroundColor.getValue());
        //testDrawLine();
       // myView.setStyle("-fx-background-color: red");
       // System.out.println(myTurtle.getView().getLayoutX());
        //System.out.println(myTurtle.getView().getTranslateX());

    }

    public void setBackgroundColor(String color) {
        myView.setBackground(new Background(new BackgroundFill(Color.valueOf(color), CornerRadii.EMPTY, Insets.EMPTY)));
    }

    protected void fitCanvas()
    {
        //background.setWidth(myView.getWidth());
        System.out.println(myView.getHeight());
        //background.setHeight(myView.getHeight()/2);
        testDrawLine();
        myTurtle.updatePosition();

    }


    private void testDrawLine()
    {

        GraphicsContext gc = background.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.strokeLine(0,0,100,100);
        gc.strokeLine(0,0,100,0);


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
    public Node getView() {
        return myView;
    }
}
