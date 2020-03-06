package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class GetShape implements Command<Double> {

    private BackEndTurtle myTurtle;

    public GetShape(BackEndTurtle t) {
        this.myTurtle = t;
    }

    @Override
    public Double execute() {
        return myTurtle.getShapeIndex().getValue();
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
