package slogo.model.backEndInternal.commands.Math;

import slogo.model.backEndInternal.commands.Command;

public class Remainder implements Command<Double> {

  private double value1;
  private double value2;

  Remainder(double v1, double v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Double execute() {
    return value1 % value2;
  }
}
