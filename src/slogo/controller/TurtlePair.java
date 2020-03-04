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
        myBackEndTurtle.getActiveProperty().bind(myViewTurtle.getActiveProperty());


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
