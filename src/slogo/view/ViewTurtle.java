package slogo.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import slogo.model.Coordinate;

public class ViewTurtle {

    private Image myImage;
    private ImageView myView;
    private Coordinate myCoordinates;

    public ViewTurtle()
    {
        myImage = new Image("turtle.jpg");
        myView = new ImageView(myImage);
        myView.setFitWidth(50);
        myView.setFitHeight(50);
        myCoordinates = new Coordinate();
    }

    public Node getView()
    {
        return myView;
    }

    public void updatePosition(Coordinate updatedCord)
    {
        myCoordinates = updatedCord;
    }

}
