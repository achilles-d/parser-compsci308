package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class Right implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;

  public Right(BackEndTurtle turtle, double angle) {
    this.backEndTurtle = turtle;
    this.degrees = angle;
  }

  @Override
  public Double execute() {
    backEndTurtle.setHeading(backEndTurtle.getHeading() + degrees);
    return degrees;
  }
}
