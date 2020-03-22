package slogo.view.components;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.model.turtle.Coordinate;

import java.util.ResourceBundle;

/**
 * @author Saurav Sanjay
 * Turtle object utilized by the front-end view only
 */

public class ViewTurtle {

    private static final String TURTLE_IMAGES = "resources.TurtleImage";
    private static final int X_LAYOUT_SCALING = 375;
    private static final double Y_LAYOUT_SCALING = 286.5;
    private static final String DEFAULT_IMAGE = "turtle.jpg";
    private static final int ACTIVE = 1;
    private static final double INACTIVE = 0.5;
    private static final int XBORDER = 750;
    private static final int YBORDER = 573;
    private static final int INITIAL_VALUE = 7;
    private static final int INITAL_PEN_SIZE = 5;
    private static final int INTIAL_SHAPE = 0;
    private Image myImage;
    private SimpleStringProperty imageName;
    private SimpleBooleanProperty activeTurtle;
    private ImageView myView;
    private Coordinate myCoordinates;
    private double myX;
    private double myY;
    private double myHeading;
    private boolean turtleVisibility;
    private ResourceBundle turtleImages;



    private SimpleDoubleProperty penColorIndex;
    private SimpleDoubleProperty shapeIndex;
    private SimpleDoubleProperty penSize;
    private SimpleBooleanProperty penStatus;

    private int size;
    private int myID;

    /**
     * Creates a new ViewTurtle object with associated id and image palette
     * @param id
     * @param imageFile
     */
    public ViewTurtle(int id,String imageFile)
    {
        myID = id;
        turtleImages = ResourceBundle.getBundle(imageFile);

        size = 50;
        activeTurtle = new SimpleBooleanProperty(true);
        penColorIndex = new SimpleDoubleProperty(INITIAL_VALUE);
        penSize = new SimpleDoubleProperty(INITAL_PEN_SIZE);
        shapeIndex = new SimpleDoubleProperty(INTIAL_SHAPE);
        penStatus = new SimpleBooleanProperty(true);

        shapeIndex.addListener((observable, oldValue, newValue) -> {setImageWithIndex((int)shapeIndex.get());});

        setImageProperty(new SimpleStringProperty(DEFAULT_IMAGE));

        myImage = new Image(this.getClass().getClassLoader().getResourceAsStream(imageName.getValue()));
        myView = new ImageView(myImage);
        myView.setFitWidth(size);
        myView.setFitHeight(size);
        myView.setOnMouseClicked(e ->{clickedTurtle();});
        myCoordinates = new Coordinate(0,0);
        updatePosition(myCoordinates);
        myHeading = 0;

    }

    /**
     * Increments pen size by given number
     * @param change
     */
    public void changePenSize(int change)
    {
        if(penSize.get()+change >=1)
            penSize.setValue(penSize.get()+change);
    }


    private void clickedTurtle()
    {
        activeTurtle.setValue(!activeTurtle.get());

        if(activeTurtle.get())
        {
            myView.setOpacity(ACTIVE);
        }
        else
        {
            myView.setOpacity(INACTIVE);
        }

    }

    /**
     * Returns pen size
     * @return property associated with pen size value
     */
    public SimpleDoubleProperty getPenSizeProperty()
    {
        return penSize;
    }

    /**
     * Returns pen status property
     * @return property associated with pen status
     */
    public SimpleBooleanProperty getPenStatusProperty()
    {
        return penStatus;
    }

    /**
     * Returns index of pen color
     * @return pen color index
     */
    public double getPenColorIndex()
    {
        return penColorIndex.get();
    }

    /**
     * sets pen color with given index
     * @param i index of color in palette
     */
    public void setPenColorIndex(int i)
    {
        penColorIndex.setValue(i);
    }

    private void setImageWithIndex(int i)
    {
        imageName.setValue(turtleImages.getString(i+""));
    }

    private int getIndexOfImage(String imageName)
    {
        for(String index: turtleImages.keySet())
        {
            if(turtleImages.getString(index).equals(imageName))
                return Integer.parseInt(index);
        }

        return 1;
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

    /**
     * returns Node object to be displayed
     * @return Node for display
     */
    public Node getView()
    {
        return myView;
    }

    /**
     * Sets heading of turtle
     * @param heading is new heading for turtle
     */
    public void setHeading(double heading)
    {
        myHeading = heading;
        myView.setRotate(myHeading);
    }

    /**
     * Returns the current heading of turtle
     * @return the turtle heading
     */
    public double getHeading()
    {
        return myHeading;
    }

    /**
     * Sets whether or not turtle is visible to user
     * @param visible indicates visibility of turtle
     */
    public void setVisibility(boolean visible)
    {
        turtleVisibility = visible;
        myView.setVisible(turtleVisibility);
    }

    /**
     * Returns property associated with pen color
     * @return property for pen color
     */
    public SimpleDoubleProperty getPenColorProperty()
    {
        return penColorIndex;
    }

    /**
     * Returns property associated with image of turtle
     * @return property for turtle image
     */
    public SimpleDoubleProperty getShapeProperty()
    {
        return shapeIndex;
    }

    /**
     * Returns property indicating if turtle is active or not
     * @return
     */
    public SimpleBooleanProperty getActiveProperty()
    {
        return activeTurtle;
    }

    /**
     * returns property indicating image name of turtle
     * @param name name of image for turtle
     */
    public void setImageProperty(SimpleStringProperty name)
    {
        imageName = name;
        imageName.addListener(e-> changeImage());
    }

    private void changeImage() {
        if (activeTurtle.get()) {
            updateImage(imageName.getValue());
            shapeIndex.setValue(getIndexOfImage(imageName.getValue()));
        }
    }

    /**
     * Returns current coordinates of ViewTurtle
     * @return turtles coordinates
     */
    public Coordinate getCoordinates()
    {
        return myCoordinates;
    }

    /**
     * Updates position of turtle with new coordinates
     * @param a new coordinates of turtle
     */
    public void updatePosition(Coordinate a) {
        myCoordinates = a;
        setXY();

        while (myX > XBORDER - myView.getFitWidth() / 2)
            myX--;
        while (myY > YBORDER - myView.getFitHeight())
            myY--;

        while (myX < 0)
            myX++;
        while (myY < 0)
            myY++;

        myView.setLayoutX(myX);
        myView.setLayoutY(myY);
    }

    /**
     * Returns ID of this ViewTurtle
     * @return ID number for this ViewTurtle
     */
    public int getID()
    {
        return myID;
    }



}
