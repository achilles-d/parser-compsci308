package slogo.view;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewTurtle {

    private Image myImage;
    private ImageView myView;

    public ViewTurtle()
    {
        myImage = new Image("turtle.jpg");
        myView = new ImageView(myImage);
        myView.setFitWidth(50);
        myView.setFitHeight(50);
    }

    public Node getView()
    {
        return myView;
    }

}
