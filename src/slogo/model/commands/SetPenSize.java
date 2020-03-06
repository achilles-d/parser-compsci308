package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class SetPenSize implements Command<Double> {

    private BackEndTurtle myTurtle;
    private Command size;

    public SetPenSize(BackEndTurtle t, Command s) {
        this.myTurtle = t;
        this.size = s;
    }

    @Override
    public Double execute() {
        Double ps = (Double) size.execute();
        myTurtle.setPenSize(ps);
        return ps;
    }

    @Override
    public boolean isItExecutable() {
        return false;
    }
}
