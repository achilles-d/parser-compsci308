package slogo.view;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import slogo.model.Coordinate;

import java.util.ResourceBundle;

public class ViewTurtle {

    private static final String TURTLE_IMAGES = "resources.TurtleImage";
    private static final int X_LAYOUT_SCALING = 375;
    private static final double Y_LAYOUT_SCALING = 286.5;
    private Image myImage;
    private SimpleStringProperty imageName;
    private SimpleBooleanProperty activeTurtle;
    private ImageView myView;
    private Coordinate myCoordinates;
    private double myX;
    private double myY;
    private double myHeading;
    private boolean turtleVisibility;
    private ResourceBundle turtleImages = java.util.ResourceBundle.getBundle(TURTLE_IMAGES);



    private SimpleDoubleProperty penColorIndex;
    private SimpleDoubleProperty shapeIndex;

    private int size;
    private int myID;

    public ViewTurtle(int id)
    {
        myID = id;
        size = 50;
        activeTurtle = new SimpleBooleanProperty(true);
        penColorIndex = new SimpleDoubleProperty(0);
        shapeIndex = new SimpleDoubleProperty(0);
        shapeIndex.addListener((observable, oldValue, newValue) -> {setImageWithIndex((int)newValue);});

        setImageProperty(new SimpleStringProperty("turtle.jpg"));

        myImage = new Image(this.getClass().getClassLoader().getResourceAsStream(imageName.getValue()));
        myView = new ImageView(myImage);
        myView.setFitWidth(size);
        myView.setFitHeight(size);
        myCoordinates = new Coordinate(0,0);
        updatePosition(myCoordinates);
        myHeading = 0;

    }

    public double getPenColorIndex()
    {
        return penColorIndex.get();
    }

    public void setPenColorIndex(int i)
    {
        penColorIndex.setValue(i);
    }

    private void setImageWithIndex(int i)
    {
        imageName.setValue(turtleImages.getString(i+""));
    }

    public int getSize()
    {
        return size;
    }

    private void setXY()
    {
        myX = myCoordinates.getXVal()+ X_LAYOUT_SCALING - myView.getFitWidth()/2;
        myY = -myCoordinates.getYVal()+ Y_LAYOUT_SCALING - myView.getFitHeight()/2;
    }

    private void updateImage(String imgName)
    {
        imgName = imgName.replaceAll("\"","");
        myImage = new Image(imgName);
        myView.setImage(myImage);
    }

    public Node getView()
    {
        return myView;
    }

    public void setHeading(double heading)
    {
        myHeading = heading;
        myView.setRotate(myHeading);
    }

    public void setVisibility(boolean visible)
    {
        turtleVisibility = visible;
        myView.setVisible(turtleVisibility);
    }

    public SimpleDoubleProperty getPenColorProperty()
    {
        return penColorIndex;
    }

    public SimpleDoubleProperty getShapeProperty()
    {
        return shapeIndex;
    }

    public SimpleBooleanProperty getActiveProperty()
    {
        return activeTurtle;
    }

    public void setImageProperty(SimpleStringProperty name)
    {
        imageName = name;
        imageName.addListener((observable, oldValue, newValue) -> { if(activeTurtle.get())
        {updateImage(imageName.getValue());}});
    }

    public void updatePosition(Coordinate a) {
        myCoordinates = a;
        setXY();
        while (myX > 750 - myView.getFitWidth() / 2)
            myX--;
        while (myY > 573 - myView.getFitHeight())
            myY--;

        while (myX < 0)
            myX++;
        while (myY < 0)
            myY++;
        myView.setLayoutX(myX);
        myView.setLayoutY(myY);
        System.out.println("Xcord " + myX);
        System.out.println("Ycord " + myY);
    }

    public int getID()
    {
        return myID;
    }



}
