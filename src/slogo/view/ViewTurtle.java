package slogo.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.model.Coordinate;

public class ViewTurtle {

    private static final int X_LAYOUT_SCALING = 375;
    private static final double Y_LAYOUT_SCALING = 286.5;
    private Image myImage;
    private SimpleStringProperty imageName;
    private ImageView myView;
    private Coordinate myCoordinates;
    private double myX;
    private double myY;
    private double myHeading;
    private boolean turtleVisibility;

    public ViewTurtle(Property turtleImage)
    {
        imageName = new SimpleStringProperty();
        //imageName.bind(turtleImage);
        imageName = (SimpleStringProperty)turtleImage;
        imageName.addListener((observable, oldValue, newValue) -> { updateImage(imageName.getValue());
            System.out.println(imageName.getValue());});

        myImage = new Image(this.getClass().getClassLoader().getResourceAsStream(imageName.getValue()));
        myView = new ImageView(myImage);
        myView.setFitWidth(50);
        myView.setFitHeight(50);
        myCoordinates = new Coordinate(0,0);
        updatePosition(myCoordinates);
        myHeading = 0;

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

    public void updatePosition(Coordinate a)
    {
        myCoordinates =a;
        setXY();
        while(myX>750-myView.getFitWidth()/2)
            myX--;
        while(myY>573-myView.getFitHeight())
            myY--;

        while(myX<0)
            myX++;
        while(myY<0)
            myY++;
        myView.setLayoutX(myX);
        myView.setLayoutY(myY);
        //System.out.println("Xcord " + x);
        //System.out.println("Ycord " + y);



    }

}
