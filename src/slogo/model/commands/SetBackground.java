package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class SetBackground implements Command<Double> {

    private BackEndTurtle myTurtle;
    private Command index;

    /**
     * command constructor
     * @param t backend turtle used to change
     * @param i constant passed
     */
    public SetBackground(BackEndTurtle t, Command i) {
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
        myTurtle.setBackgroundColorIndex(i);
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
