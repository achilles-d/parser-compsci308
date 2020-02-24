package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class IsShowing implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public IsShowing(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return backEndTurtle.getVisibility() ? 1.0 : 0.0;
  }
}