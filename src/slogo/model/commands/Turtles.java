package slogo.model.commands;

import slogo.controller.TurtleController;

public class Turtles implements Command {

    private TurtleController tr;
    public Turtles(TurtleController tr) {
        this.tr=tr;
    }
    @Override
    public Object execute() {

        return (double)tr.getNumberOfTurtles();
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
