package slogo.model.backEndInternal;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import slogo.model.Coordinate;
import slogo.model.Line;
import slogo.model.Turtle;
import slogo.view.ViewController;
import slogo.view.ViewTurtle;
import slogo.view.ViewTurtlePlan;

import java.util.ArrayList;
import java.util.List;

public class BackEndTurtle implements Turtle {

    private static final int XBOUNDS = 342;
    private static final int YBOUNDS = 254;


    private SimpleDoubleProperty xLoc = new SimpleDoubleProperty();
    private SimpleDoubleProperty yLoc = new SimpleDoubleProperty();

    private SimpleDoubleProperty endX = new SimpleDoubleProperty();
    private SimpleDoubleProperty endY = new SimpleDoubleProperty();

    private SimpleDoubleProperty heading = new SimpleDoubleProperty();
    private SimpleBooleanProperty penDown = new SimpleBooleanProperty();
    private SimpleBooleanProperty turtleVisible = new SimpleBooleanProperty();




    //private double xLoc;
    //private double yLoc;
    //private double endX;
    //private double endY;

    private Coordinate turtleCoordinate;
    private List<Line> lines;

    //private double heading;
    //private boolean penUp;
    //private boolean turtleVisible;

    public BackEndTurtle(){
        lines=new ArrayList<>();
        turtleCoordinate = new Coordinate();
        xLoc.set(turtleCoordinate.getXVal());
        yLoc.set(turtleCoordinate.getYVal());
        penDown.set(true);
        turtleVisible.set(true);

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
        System.out.println(" before " + turtleCoordinate);
        turtleCoordinate = newCord;
        System.out.println( " after " + turtleCoordinate);
        xLoc.set(newCord.getXVal());
        yLoc.set(newCord.getYVal());
    }

    //https://codereview.stackexchange.com/questions/58063/screen-wraparound
    private Coordinate wrapAround(Coordinate a){
        double x = a.getXVal();
        double y = a.getYVal();

        System.out.println("OLD X " + x);
        System.out.println("OLD Y " + y);

        if(x/XBOUNDS >1)
        {
            x =-XBOUNDS+x%XBOUNDS;
        }
        else
            x = x%(XBOUNDS+1);

        if(y/YBOUNDS >1)
        {
            y= -YBOUNDS +y%YBOUNDS;
        }
        else
            y = y%(YBOUNDS+1);
        y = y%(YBOUNDS+1);

        /*
        if(x<0)
        {
            x+=XBOUNDS;
        }
        if(y<0)
        {
            y+=YBOUNDS;
        }

         */
        return new Coordinate(x,y);
    }
    private Coordinate ensureInBounds(Coordinate a) {

        double x = a.getXVal();
        double xPrev = x;
        double y = a.getYVal();
        double yPrev = y;

        boolean hitXWall = false;
        boolean hitYWall = false;


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

        System.out.println("YAY " );

        return new Coordinate((!hitXWall && hitYWall? (y-turtleCoordinate.getYVal())*Math.tan(Math.toRadians(heading.getValue()))+turtleCoordinate.getXVal():x),(hitXWall && !hitYWall? (x-turtleCoordinate.getXVal())/Math.tan(Math.toRadians(heading.getValue()))+turtleCoordinate.getYVal():y));

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
        heading.set(changeHeading);
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
        LineAPI line=new LineAPI(start,end);
        lines.add(line);
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

    public BooleanProperty getPenVisibility() { return penDown; }

    public boolean getPenStatus() {
        return penDown.get();
    }

    public boolean getVisibility() {
        return turtleVisible.get();
    }
}
