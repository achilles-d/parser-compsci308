package slogo.controller;


import slogo.view.components.ViewTurtle;
import slogo.model.turtle.BackEndTurtle;


public class TurtlePair {

    private ViewTurtle myViewTurtle;
    private BackEndTurtle myBackEndTurtle;

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
     * Returns a ViewTurtle object associated with this pair of Turtles
     * @return ViewTurtle in pair
     */
    public ViewTurtle getViewTurtle()
    {
        return myViewTurtle;
    }

    /**
     * Returns a BackEnd Turtle object associated with this pair of Turtles
     * @return BackEndTurtle in pair
     */
    public BackEndTurtle getBackEndTurtle()
    {
        return myBackEndTurtle;
    }
}
