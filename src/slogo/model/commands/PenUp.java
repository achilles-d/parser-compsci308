package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class PenUp implements Command<Double> {

  BackEndTurtle backEndTurtle;

  /**
   * command constructor
   * @param myTurtle backend turtle to apply to
   */
  public PenUp(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    backEndTurtle.setPen(false);
    return 0.0;
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
