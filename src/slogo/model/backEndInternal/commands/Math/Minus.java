package slogo.model.backEndInternal.commands.Math;

import slogo.model.backEndInternal.commands.Command;

public class Minus implements Command<Double> {

  private double value;

  Minus(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return value * -1;
  }
}
