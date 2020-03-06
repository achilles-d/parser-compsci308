package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class SetBackground implements Command<Double> {

    private BackEndTurtle myTurtle;
    private Command index;

    public SetBackground(BackEndTurtle t, Command i) {
        this.myTurtle = t;
        this.index = i;
    }

    @Override
    public Double execute() {
        Double i = (Double) index.execute();
        myTurtle.setBackgroundColorIndex(i);
        return i;
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
