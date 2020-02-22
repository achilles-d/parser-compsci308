package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.BackEndTurtle;

public class Left implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;

  Left(BackEndTurtle turtle, double angle) {
    this.backEndTurtle = turtle;
    this.degrees = angle;
  }

  @Override
  public Double execute() {
    backEndTurtle.setHeading(backEndTurtle.getHeading() - degrees);
    return degrees;
  }
}
