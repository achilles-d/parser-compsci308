package slogo.view;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.model.Coordinate;

public class ViewTurtle {

    private Image myImage;
    private SimpleStringProperty imageName;
    private ImageView myView;
    private Coordinate myCoordinates;
    private double myX;
    private double myY;
    private double myHeading;

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
        myCoordinates = new Coordinate();
        myX = myCoordinates.getXVal();
        myY = myCoordinates.getYVal();
        myHeading = 0;

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

    public void updatePosition()
    {
        myView.setX(myX);
        myView.setY(myY);
    }

}
