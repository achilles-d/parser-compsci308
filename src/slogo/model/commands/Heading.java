package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class Heading implements Command<Double> {

  BackEndTurtle backEndTurtle;

  /**
   * Command Constructor
   * @param myTurtle which backend turtle to apply to
   */
  public Heading(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    return backEndTurtle.getHeading();
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