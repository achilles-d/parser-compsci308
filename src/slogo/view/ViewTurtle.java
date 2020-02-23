package slogo.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.model.Coordinate;

public class ViewTurtle {

    private Image myImage;
    private ImageView myView;
    private Coordinate myCoordinates;
    private double myX;
    private double myY;

    public ViewTurtle()
    {
        myImage = new Image("turtle.jpg");
        myView = new ImageView(myImage);
        myView.setFitWidth(50);
        myView.setFitHeight(50);
        myCoordinates = new Coordinate();
        myX = myCoordinates.getXVal();
        myY = myCoordinates.getYVal();
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
