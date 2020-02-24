package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class Right implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;

  public Right(BackEndTurtle turtle, double angle) {
    this.backEndTurtle = turtle;
    this.degrees = angle;
  }

  @Override
  public double execute() {
    backEndTurtle.setHeading(backEndTurtle.getHeading() + degrees);
    return degrees;
  }
}
