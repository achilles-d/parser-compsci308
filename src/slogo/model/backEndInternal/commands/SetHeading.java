package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class SetHeading implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;

  public SetHeading(BackEndTurtle turtle, double angle) {
    this.backEndTurtle = turtle;
    this.degrees = angle;
  }

  @Override
  public Double execute() {
    Double calcDegrees = Math.abs(backEndTurtle.getHeading() - degrees);
    backEndTurtle.setHeading(degrees);
    return calcDegrees;
  }
}
