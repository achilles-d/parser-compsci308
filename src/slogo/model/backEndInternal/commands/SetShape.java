package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class SetShape implements Command<Double> {

    BackEndTurtle myTurtle;
    Command index;

    public SetShape(BackEndTurtle t, Command i) {
        this.myTurtle = t;
        this.index = i;
    }

    @Override
    public Double execute() {
        return null;
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
