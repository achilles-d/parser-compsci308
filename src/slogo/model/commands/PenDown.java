package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class PenDown implements Command<Double> {

  BackEndTurtle backEndTurtle;

  /**
   * Command constructor
   * @param myTurtle back end turtle used
   */
  public PenDown(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    backEndTurtle.setPen(true);
    return 1.0;
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
