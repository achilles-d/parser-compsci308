package slogo.model.backEndInternal.commands.Math;

import slogo.model.backEndInternal.commands.Command;

public class ArcTangent implements Command<Double> {

  private double value;

  ArcTangent(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return Math.atan(value);
  }
}
