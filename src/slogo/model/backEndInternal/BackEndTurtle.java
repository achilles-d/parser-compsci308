package slogo.model.backEndInternal;

import slogo.model.Coordinate;
import slogo.model.Line;
import slogo.model.Turtle;
import slogo.view.ViewController;
import slogo.view.ViewTurtle;
import slogo.view.ViewTurtlePlan;

import java.util.List;

public class BackEndTurtle implements Turtle {
    private double xLoc;
    private double yLoc;
    private double endX;
    private double endY;

    private double heading;
    private boolean penUp;
    public BackEndTurtle(){
        xLoc=0;
        yLoc=0;
        penUp=false;

        ViewTurtle turtle=new ViewTurtle();


    }
    @Override
    /**
     *  position of the turtle based on a coordinate it is given
     * @param a is turtle's new coordinate
     */
    public void setPosition(Coordinate a) {

        xLoc=a.getX();
        yLoc=a.getY();
    }

    @Override
    public Coordinate getPosition() {
        return null;
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

    @Override
    /**
     * This makes the turtle draw a new line object when it moves, and the pen is also down
     * @param start is the starting coordinate of the line
     * @param end is the ending coordinate of the line
     */
    public void drawLine(Coordinate start, Coordinate end) {
        penUp=false;
        xLoc=end.getX();
        yLoc=end.getY();
    }

    @Override
    public List<Line> getLines() {
        return null;
    }
}
