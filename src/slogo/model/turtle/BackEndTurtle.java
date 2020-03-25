package slogo.model.turtle;

import javafx.beans.property.*;
import slogo.model.interfaces.Turtle;

import java.util.ArrayList;
import java.util.List;

public class BackEndTurtle implements Turtle {

    private static final int XBOUNDS = 342;
    private static final int YBOUNDS = 254;


    private SimpleDoubleProperty xLoc = new SimpleDoubleProperty();
    private SimpleDoubleProperty yLoc = new SimpleDoubleProperty();


    private SimpleDoubleProperty heading = new SimpleDoubleProperty();
    private SimpleBooleanProperty penDown = new SimpleBooleanProperty();
    private SimpleBooleanProperty turtleVisible = new SimpleBooleanProperty();
    private SimpleBooleanProperty activeTurtle = new SimpleBooleanProperty();

    private SimpleDoubleProperty penColor = new SimpleDoubleProperty();
    private SimpleDoubleProperty backgroundColor = new SimpleDoubleProperty();
    private SimpleDoubleProperty shapeIndex = new SimpleDoubleProperty();
    private SimpleDoubleProperty penSize = new SimpleDoubleProperty();


    private Coordinate turtleCoordinate;
    private List<Line> lines;
    private int myID;


    /**
     * Constructor to create a new BackEndTurtle instance.
     * @param id Id to match between front and backend pair.
     */
    public BackEndTurtle(int id){
        myID = id;
        lines=new ArrayList<>();
        turtleCoordinate = new Coordinate();
        xLoc.set(turtleCoordinate.getXVal());
        yLoc.set(turtleCoordinate.getYVal());
        penDown.set(true);
        turtleVisible.set(true);
        activeTurtle.set(true);

    }


    /**
     *  position of the turtle based on a coordinate it is given
     * @param a is turtle's new coordinate
     */
    @Override
    public void setPosition(Coordinate a) {

        Coordinate newCord = ensureInBounds(a);

        if(penDown.getValue())
        {
            drawLine(turtleCoordinate,newCord);
        }
        turtleCoordinate = newCord;
        xLoc.set(newCord.getXVal());
        yLoc.set(newCord.getYVal());
    }

    private Coordinate ensureInBounds(Coordinate a) {
        double x = a.getXVal();
        double y = a.getYVal();
        boolean hitXWall = false;
        boolean hitYWall = false;
        return getCoordinate(x, y, hitXWall, hitYWall);
    }

    private Coordinate getCoordinate(double x, double y, boolean hitXWall, boolean hitYWall) {
        while (x > XBOUNDS) {
            x--;
            hitXWall = true;
        }
        while (x < -XBOUNDS) {
            x++;
            hitXWall = true;
        }
        while (y > YBOUNDS){
            y--;
            hitYWall = true;
        }
        while (y < -YBOUNDS){
            y++;
            hitYWall = true;
        }
        if(!hitXWall && hitYWall)
        {
           x =  (y-turtleCoordinate.getYVal())*Math.tan(Math.toRadians(heading.getValue()))+turtleCoordinate.getXVal();
        }
        else if(hitXWall && !hitYWall)
            y = (x-turtleCoordinate.getXVal())/Math.tan(Math.toRadians(heading.getValue()))+turtleCoordinate.getYVal();

        return new Coordinate(x,y);
    }

    /**
     * Getter to obtain turtle position.
     * @return A coordinate object that contains location info.
     */
    @Override
    public Coordinate getPosition() {
        return turtleCoordinate;
    }

    /**
     * Getter to obtain turtle heading.
     * @return Double that gives heading
     */
    @Override
    public double getHeading() {
        return heading.doubleValue();
    }

    @Override
    /**
     * will set the heading of the turtle
     * to the new heading given
     * @param changeHeading is the new heading of the Turtle
     */
    public void setHeading(double changeHeading) {
        heading.set(changeHeading%360);
    }

    /**
     * This will toggle the pen on or off which changes whether or not a line is drawn when the turtle moves
     *
     */
    @Override
    public void setPen(boolean penState) {
        penDown.set(penState);
    }

    /**
     * sets the backend turtle active
     * @param b backend turtle
     */
    public void setActiveTurtle(Boolean b) { this.activeTurtle.set(b); }

    /**
     * Toggle whether turtle is visible or not
     */
    public void setVisibility(boolean visible) {
        turtleVisible.set(visible);
    }

    /**
     * This makes the turtle draw a new line object when it moves, and the pen is also down
     * @param start is the starting coordinate of the line
     * @param end is the ending coordinate of the line
     */
    @Override
    public void drawLine(Coordinate start, Coordinate end) {
        Line line=new Line(start,end);
        lines.add(line);
    }

    /**
     * Set's pen color for back end turtle.
     * @param color Index for color
     */
    public void setPenColorIndex(Double color) {
        this.penColor.set(color);
    }

    /**
     * Set's background color through command.
     * @param color Index for color
     */
    public void setBackgroundColorIndex(Double color) {
        this.backgroundColor.set(color);
    }

    /**
     * Set shape through command.
     * @param shape Index for shape.
     */
    public void setShapeIndex(Double shape) { this.shapeIndex.set(shape); }

    /**
     * Set's pensize in backend turtle for command input.
     * @param thickness Double for pen thickness
     */
    public void setPenSize(Double thickness){
        this.penSize.set(thickness);
    }

    /**
     * Getter to return list of lines to draw
     * @return List of lines
     */
    @Override
    public List<Line> getLines() {
        return lines;
    }

    /**
     * Property binding between front and back end
     * @return double property for binding
     */
    public DoubleProperty getXLocProp() {
        return xLoc;
    }

    /**
     * Property binding between front and back end
     * @return double property for binding
     */
    public DoubleProperty getYLocProp() {
        return yLoc;
    }

    /**
     * Property binding between front and back end
     * @return double property for binding
     */
    public DoubleProperty getHeadingProp() { return heading; }

    /**
     * Property binding between front and back end
     * @return boolean property for binding
     */
    public BooleanProperty getTurtleVisibility() { return turtleVisible; }

    /**
     * Property binding between front and back end
     * @return boolean property for binding
     */
    public BooleanProperty getPenVisibilityProperty() { return penDown; }

    /**
     * Property binding between front and back end
     * @return double property for binding
     */
    public DoubleProperty getTurtleColor() { return penColor; }

    /**
     * Property binding between front and back end
     * @return double property for binding
     */
    public DoubleProperty getBackgroundColor() { return backgroundColor; }

    /**
     * Property binding between front and back end
     * @return double property for binding
     */
    public DoubleProperty getShapeIndex() { return shapeIndex; }

    /**
     * Property binding between front and back end
     * @return double property for binding
     */
    public DoubleProperty getPenSize() { return penSize; }

    /**
     * Getter for penstatus
     * @return is pen up or down
     */
    public boolean getPenStatus() {
        return penDown.get();
    }

    /**
     * Getter for turtle visibility
     * @return is turtle visible or not
     */
    public boolean getVisibility() {
        return turtleVisible.get();
    }

    /**
     * Property binding between front and back end
     * @return boolean property for binding
     */
    public BooleanProperty getActiveProperty()
    {
        return activeTurtle;
    }

    /**
     * Property binding between front and back end
     * @return boolean property for binding
     */
    public DoubleProperty getPenSizeProperty()
    {
        return penSize;
    }


}
