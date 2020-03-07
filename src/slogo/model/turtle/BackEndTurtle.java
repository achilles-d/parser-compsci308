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


    @Override
    public Coordinate getPosition() {
        return turtleCoordinate;
    }

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

    public void setPenColorIndex(Double color) {
        this.penColor.set(color);
    }

    public void setBackgroundColorIndex(Double color) {
        this.backgroundColor.set(color);
    }

    public void setShapeIndex(Double shape) { this.shapeIndex.set(shape); }

    public void setPenSize(Double thickness){
        this.penSize.set(thickness);
    }

    @Override
    public List<Line> getLines() {
        return lines;
    }

    public DoubleProperty getXLocProp() {
        return xLoc;
    }

    public DoubleProperty getYLocProp() {
        return yLoc;
    }

    public DoubleProperty getHeadingProp() { return heading; }

    public BooleanProperty getTurtleVisibility() { return turtleVisible; }

    public BooleanProperty getPenVisibilityProperty() { return penDown; }

    public DoubleProperty getTurtleColor() { return penColor; }

    public DoubleProperty getBackgroundColor() { return backgroundColor; }

    public DoubleProperty getShapeIndex() { return shapeIndex; }

    public DoubleProperty getPenSize() { return penSize; }

    public boolean getPenStatus() {
        return penDown.get();
    }

    public boolean getVisibility() {
        return turtleVisible.get();
    }

    public BooleanProperty getActiveProperty()
    {
        return activeTurtle;
    }

    public DoubleProperty getPenSizeProperty()
    {
        return penSize;
    }


}
