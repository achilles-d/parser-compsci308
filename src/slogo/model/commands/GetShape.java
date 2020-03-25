package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class GetShape implements Command<Double> {

    private BackEndTurtle myTurtle;

    /**
     * Command Constructor
     * @param t backend turtle to get info
     */
    public GetShape(BackEndTurtle t) {
        this.myTurtle = t;
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Double execute() {
        return myTurtle.getShapeIndex().getValue();
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
