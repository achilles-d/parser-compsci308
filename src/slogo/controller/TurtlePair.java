package slogo.controller;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.view.ViewTurtle;

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
    }

    public ViewTurtle getViewTurtle()
    {
        return myViewTurtle;
    }

    public BackEndTurtle getBackEndTurtle()
    {
        return myBackEndTurtle;
    }
}
