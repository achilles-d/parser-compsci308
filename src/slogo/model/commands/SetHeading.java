package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class SetHeading implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;
  private Command cmd;

  /**
   * command constructor
   * @param turtle backend turtle used to change
   * @param cmd constant passed
   */
  public SetHeading(BackEndTurtle turtle, Command cmd) {
    this.backEndTurtle = turtle;
    this.cmd = cmd;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    degrees= (double) cmd.execute();
    Double calcDegrees = Math.abs(backEndTurtle.getHeading() - degrees);
    backEndTurtle.setHeading(degrees);
    return calcDegrees;
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
