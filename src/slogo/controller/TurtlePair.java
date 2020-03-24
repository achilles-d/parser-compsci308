package slogo.controller;


import slogo.view.components.ViewTurtle;
import slogo.model.turtle.BackEndTurtle;

/**
 * A class that pairs a turtle's ViewTurtle object with its BackEndTurtle object
 * The values of the TurtleController's instance variable, turtleMap, are instances of this class
 *
 * @author Achilles Dabrowski
 */
public class TurtlePair {

    private ViewTurtle myViewTurtle;
    private BackEndTurtle myBackEndTurtle;

    /**
     * Create a new TurtlePair object.
     * @param view the ViewTurtle object corresponding to the turtle of interest
     * @param backend the BackEndTurtle object corresponding to the turtle of interest
     */
    public TurtlePair(ViewTurtle view, BackEndTurtle backend)
    {
        myBackEndTurtle = backend;
        myViewTurtle = view;
        connectTurtles();
    }

    private void connectTurtles()
    {
        myBackEndTurtle.getActiveProperty().bindBidirectional(myViewTurtle.getActiveProperty());
        myBackEndTurtle.getTurtleColor().bindBidirectional(myViewTurtle.getPenColorProperty());
        myBackEndTurtle.getShapeIndex().bindBidirectional(myViewTurtle.getShapeProperty());
        myBackEndTurtle.getPenSizeProperty().bindBidirectional(myViewTurtle.getPenSizeProperty());
        myBackEndTurtle.getPenVisibilityProperty().bindBidirectional(myViewTurtle.getPenStatusProperty());
    }

    /**
     * Return the ViewTurtle object corresponding to the turtle
     * @return the ViewTurtle object corresponding to the turtle
     */
    public ViewTurtle getViewTurtle()
    {
        return myViewTurtle;
    }

    /**
     * Return the BackEndTurtle object corresponding to the turtle
     * @return the BackEndTurtle object corresponding to the turtle 
     */
    public BackEndTurtle getBackEndTurtle()
    {
        return myBackEndTurtle;
    }
}
