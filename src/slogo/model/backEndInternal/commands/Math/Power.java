package slogo.model.backEndInternal.commands.Math;

import slogo.model.backEndInternal.commands.Command;

public class Power implements Command<Double> {

  private double base;
  private double exponent;

  Power(double v1, double v2) {
    this.base = v1;
    this.exponent = v2;
  }

  @Override
  public Double execute() {
    return Math.pow(base, exponent);
  }
}
