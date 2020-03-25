package slogo.model.commands;

import slogo.controller.TurtleController;

public class Turtles implements Command {

    private TurtleController tr;

    /**
     * command constructor
     * @param tr turtlecontroller used
     */
    public Turtles(TurtleController tr) {
        this.tr=tr;
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Object execute() {

        return (double)tr.getNumberOfTurtles();
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
