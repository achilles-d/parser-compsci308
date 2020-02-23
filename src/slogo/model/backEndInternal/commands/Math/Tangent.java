package slogo.model.backEndInternal.commands.Math;

import slogo.model.backEndInternal.commands.Command;

public class Tangent implements Command<Double> {

  private double value;

  Tangent(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return Math.tan(value);
  }
}
