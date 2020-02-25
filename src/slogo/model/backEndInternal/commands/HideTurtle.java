package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class HideTurtle implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public HideTurtle(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    backEndTurtle.toggleVisibility();
    return (double) 0;
  }
}
