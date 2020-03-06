package slogo.view;

import javafx.scene.image.Image;
import slogo.model.turtle.Coordinate;

public interface ViewTurtlePlan {

    /**
     * This will update the position of the turtle in the view, it will be in the front-end external API
     * @param pos is new coordinate of turtle
     */
    public void updatePosition(Coordinate pos);

    /**
     * This will update the heading of the turtle in the view, it will be in the front-end external API
     * @param head is new heading of turtle
     */
    public void updateHeading(double head);

    /**
     * This will toggle the visibility of the viewTurtle
     * It will be in the front-end external API
     */
    public void toggleVisibility();

    /**
     * This will set the image of the turtle to a user chosen image
     * @param a is the new image chosen by the user
     */
    public void setTurtleImage(Image a);


}
