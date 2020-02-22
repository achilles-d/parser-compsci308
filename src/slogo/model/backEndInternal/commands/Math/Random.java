package slogo.model.backEndInternal.commands.Math;

import slogo.model.backEndInternal.commands.Command;

public class Random implements Command<Double> {

  private double max;

  Random(double v1) {
    this.max = v1;
  }

  @Override
  public Double execute() {
    return Math.floor(Math.random() * max);
  }
}
