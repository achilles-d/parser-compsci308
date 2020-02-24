package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class SetTowards implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double x;
  private double y;

 public SetTowards(BackEndTurtle myTurtle, double val1, double val2) {
    this.backEndTurtle = myTurtle;
    this.x = val1;
    this.y = val2;
  }

  @Override
  public double execute() {
    double newHeading = Math.toDegrees(Math.atan2(y, x));
    backEndTurtle.setHeading(newHeading);
    return newHeading;
  }
}
