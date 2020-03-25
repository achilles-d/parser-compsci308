package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class YCoordinate implements Command<Double> {

  BackEndTurtle backEndTurtle;

  /**
   * command constructor
   * @param myTurtle backend turtle to get info from
   */
  public YCoordinate(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    return backEndTurtle.getPosition().getYVal();
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