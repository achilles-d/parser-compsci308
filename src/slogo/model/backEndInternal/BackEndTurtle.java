package slogo.model.backEndInternal;

import javafx.beans.property.*;
import slogo.model.Coordinate;
import slogo.model.Line;
import slogo.model.Turtle;
import slogo.view.ViewController;
import slogo.view.ViewTurtle;
import slogo.view.ViewTurtlePlan;

import java.util.ArrayList;
import java.util.List;

public class BackEndTurtle implements Turtle {



    private SimpleDoubleProperty xLoc = new SimpleDoubleProperty();
    private SimpleDoubleProperty yLoc = new SimpleDoubleProperty();

    private SimpleDoubleProperty endX = new SimpleDoubleProperty();
    private SimpleDoubleProperty endY = new SimpleDoubleProperty();

    private SimpleDoubleProperty heading = new SimpleDoubleProperty();
    private SimpleBooleanProperty penDown = new SimpleBooleanProperty();
    private SimpleBooleanProperty turtleVisible = new SimpleBooleanProperty();

    private SimpleStringProperty penColor = new SimpleStringProperty();
    private SimpleStringProperty backgroundColor = new SimpleStringProperty();


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

    private Coordinate ensureInBounds(Coordinate a) {

        double x = a.getXVal();
        double y = a.getYVal();

        boolean hitXWall = false;
        boolean hitYWall = false;


        while (x > 370) {
            x--;
            hitXWall = true;
        }
        while (x < -370) {
            x++;
            hitXWall = true;
        }

        while (y > 280){
            y--;
            hitYWall = true;

        }

        while (y < -280){
            y++;
            hitYWall = true;

        }


        return new Coordinate((!hitXWall && hitYWall? turtleCoordinate.getXVal():x),(hitXWall && !hitYWall? turtleCoordinate.getYVal():y));

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
        System.out.println("haha still drewa a line");
        LineAPI line=new LineAPI(start,end);
        lines.add(line);
    }

    public void setPenColor(String color) {
        this.penColor.set(color);
    }

    public void setBackgroundColor(String color) {
        this.backgroundColor.set(color);
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

    public StringProperty getTurtleColor() { return penColor; }

    public StringProperty getBackgroundColor() { return backgroundColor; }

    public boolean getPenStatus() {
        return penDown.get();
    }

    public boolean getVisibility() {
        return turtleVisible.get();
    }
}
