package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class SetPenColor implements Command<Double> {

    BackEndTurtle myTurtle;
    Command index;

    /**
     * command constructor
     * @param t backend turtle used to change
     * @param i constant passed
     */
    public SetPenColor(BackEndTurtle t, Command i) {
        this.myTurtle = t;
        this.index = i;
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Double execute() {
        Double i = (Double) index.execute();
        myTurtle.setPenColorIndex(i);
        return i;
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return true;
    }

}
