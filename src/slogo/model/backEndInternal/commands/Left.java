package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class Left implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;

 public Left(BackEndTurtle turtle, double angle) {
    this.backEndTurtle = turtle;
    this.degrees = angle;
  }

  @Override
  public double execute() {
    backEndTurtle.setHeading(backEndTurtle.getHeading() - degrees);
    return degrees;
  }
}
