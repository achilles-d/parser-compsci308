package slogo.model.backEndInternal.commands.Math;

import slogo.model.backEndInternal.commands.Command;

public class Pi implements Command<Double> {

  @Override
  public Double execute() {
    return Math.PI;
  }
}
