package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class GetPenColor implements Command<Double> {

    private BackEndTurtle myTurtle;

    public GetPenColor(BackEndTurtle t) {
        this.myTurtle = t;
    }

    @Override
    public Double execute() {
        return myTurtle.getTurtleColor().getValue();
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
