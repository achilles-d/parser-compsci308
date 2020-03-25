package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class Right implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;
  private Command cmd;

  /**
   * command constructor
   * @param turtle backend turtle used to change
   * @param angle constant passed
   */
  public Right(BackEndTurtle turtle,Command angle) {
    this.backEndTurtle = turtle;
    this.cmd = angle;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    degrees= (double) cmd.execute();
    backEndTurtle.setHeading(backEndTurtle.getHeading() + degrees);
    return degrees;
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
