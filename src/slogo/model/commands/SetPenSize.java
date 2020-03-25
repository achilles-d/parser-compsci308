package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class SetPenSize implements Command<Double> {

    private BackEndTurtle myTurtle;
    private Command size;

    /**
     * command constructor
     * @param t backend turtle used to change
     * @param s constant passed
     */
    public SetPenSize(BackEndTurtle t, Command s) {
        this.myTurtle = t;
        this.size = s;
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Double execute() {
        Double ps = (Double) size.execute();
        myTurtle.setPenSize(ps);
        return ps;
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return false;
    }
}
