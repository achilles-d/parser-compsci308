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
        Double i = (Double) index.execute();
        myTurtle.setShapeIndex(i);
        return i;
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
