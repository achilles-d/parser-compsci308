package slogo.model.backEndInternal.commands.Math;

import slogo.model.backEndInternal.commands.Command;

public class NaturalLog implements Command<Double> {

  private double value;

  NaturalLog(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return Math.log(value);
  }
}
