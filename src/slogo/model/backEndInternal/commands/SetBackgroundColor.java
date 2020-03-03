package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class SetBackgroundColor implements Command<Double> {

    private BackEndTurtle myTurtle;
    private Command index;

    public SetBackgroundColor(BackEndTurtle t, Command i) {
        this.myTurtle = t;
        this.index = i;
    }

    @Override
    public Double execute() {
        myTurtle.setBackgroundColor((Double) index.execute());
        return (Double) index.execute();
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
