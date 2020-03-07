package slogo.view.components;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.model.turtle.Coordinate;

import java.util.ResourceBundle;

public class ViewTurtle {

    private static final String TURTLE_IMAGES = "resources.TurtleImage";
    private static final int X_LAYOUT_SCALING = 375;
    private static final double Y_LAYOUT_SCALING = 286.5;
    private static final String DEFAULT_IMAGE = "turtle.jpg";
    private static final int ACTIVE = 1;
    private static final double INACTIVE = 0.5;
    private static final int XBORDER = 750;
    private static final int YBORDER = 573;
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

    public ViewTurtle(int id,String imageFile)
    {
        myID = id;
        turtleImages = ResourceBundle.getBundle(imageFile);

        size = 50;
        activeTurtle = new SimpleBooleanProperty(true);
        penColorIndex = new SimpleDoubleProperty(7);
        penSize = new SimpleDoubleProperty(5);
        shapeIndex = new SimpleDoubleProperty(0);
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

    public SimpleDoubleProperty getPenSizeProperty()
    {
        return penSize;
    }

    public SimpleBooleanProperty getPenStatusProperty()
    {
        return penStatus;
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

    public Node getView()
    {
        return myView;
    }

    public void setHeading(double heading)
    {
        myHeading = heading;
        myView.setRotate(myHeading);
    }

    public double getHeading()
    {
        return myHeading;
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
        imageName.addListener(e-> changeImage());
    }

    private void changeImage() {
        if (activeTurtle.get()) {
            updateImage(imageName.getValue());
            shapeIndex.setValue(getIndexOfImage(imageName.getValue()));
        }
    }
    public Coordinate getCoordinates()
    {
        return myCoordinates;
    }

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

    public int getID()
    {
        return myID;
    }



}
