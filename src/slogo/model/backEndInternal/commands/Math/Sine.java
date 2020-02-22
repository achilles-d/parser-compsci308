package slogo.model.backEndInternal.commands.Math;

import slogo.model.backEndInternal.commands.Command;

public class Sine implements Command<Double> {

  private double value;

  Sine(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return Math.sin(value);
  }
}
