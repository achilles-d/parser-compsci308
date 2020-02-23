package slogo.model.backEndInternal;

import slogo.model.Coordinate;
import slogo.model.Line;
import slogo.model.Turtle;

import java.util.List;

public class BackEndTurtle implements Turtle {
    private double xLoc;
    private double yLoc;
    private double endX;
    private double endY;

    private Coordinate turtleCoordinate = new Coordinate();

    private double heading;
    private boolean penUp;
    private boolean turtleVisible;

    public BackEndTurtle(){
        xLoc = turtleCoordinate.getXVal();
        yLoc = turtleCoordinate.getYVal();
        penUp = false;
        turtleVisible = true;
    }
    @Override
    /**
     *  position of the turtle based on a coordinate it is given
     * @param a is turtle's new coordinate
     */
    public void setPosition(Coordinate a) {
        xLoc = a.getXVal();
        yLoc = a.getYVal();
    }

    @Override
    public Coordinate getPosition() {
        return turtleCoordinate;
    }

    @Override
    public double getHeading() {
        return 0;
    }

    @Override
    /**
     * will set the heading of the turtle
     * to the new heading given
     * @param changeHeading is the new heading of the Turtle
     */
    public void setHeading(double changeHeading) {
        heading=changeHeading;
    }

    @Override
    /**
     * This will toggle the pen on or off which changes whether or not a line is drawn when the turtle moves
     *
     */
    public void flipPen() {
        penUp=!penUp;
    }

    /**
     * Toggle whether turtle is visible or not
     */
    public void toggleVisibility() {
        turtleVisible = !turtleVisible;
    }

    @Override
    /**
     * This makes the turtle draw a new line object when it moves, and the pen is also down
     * @param start is the starting coordinate of the line
     * @param end is the ending coordinate of the line
     */
    public void drawLine(Coordinate start, Coordinate end) {
        penUp=false;
        xLoc = end.getXVal();
        yLoc = end.getYVal();
    }

    @Override
    public List<Line> getLines() {
        return null;
    }
}
