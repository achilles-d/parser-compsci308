package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class IsPenDown implements Command<Double> {

  BackEndTurtle backEndTurtle;

    /**
     * command constructor
     * @param myTurtle back end turtle to use
     */
 public IsPenDown(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

    /**
     * Execution logic
     * @return Double value argument
     */
  @Override
  public Double execute() {
    return (double) (backEndTurtle.getPenStatus() ? 0 : 1);
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
