package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class IsShowing implements Command<Double> {

  BackEndTurtle backEndTurtle;

  /**
   * command constructor
   * @param myTurtle backend end turtle to use
   */
  public IsShowing(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    return (double) (backEndTurtle.getVisibility() ? 1 : 0);
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