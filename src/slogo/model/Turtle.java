package slogo.model;

import slogo.model.Coordinate;
import slogo.model.Line;

import java.util.List;

public interface Turtle {

    /**
     * This method, which is a part of the internal back-end API, will set the
     * position of the turtle based on a coordinate it is given
     * @param a is turtle's new coordinate
     */
    public void setPosition(Coordinate a);

    /**
     * This method, part of the external back-end API, will return the turtle's current position
     * @return Coordinate corresponding to Turtle position
     */
    public Coordinate getPosition();

    /**
     * This method will return the current heading of the turtle, also a part of the external back-end API
     * @return double that represents what direction in 360 turtle is pointing
     */
    public double getHeading();

    /**
     * This method, part of internal back-end API, will set the heading of the turtle
     * to the new heading given
     * @param changeHeading is the new heading of the Turtle
     */
    public void setHeading(double changeHeading);

    /**
     * This will toggle the pen on or off which changes whether or not a line is drawn when the turtle moves
     * This is part of the internal back-end API
     */
    public void flipPen();

    /**
     * This makes the turtle draw a new line object when it moves, and the pen is also down
     * This is part of the internal back-end API
     * @param start is the starting coordinate of the line
     * @param end is the ending coordinate of the line
     */
    public void drawLine(Coordinate start, Coordinate end);

    /**
     * This returns all line objects associated with a turtle, so the front-end will be able to draw the corresponding lines
     * This is a part of the external back-end API
     * @return an unmodifiable list of line objects associated with turtle
     */
    public List<Line> getLines();
}
