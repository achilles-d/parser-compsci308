package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class HideTurtle implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  public HideTurtle(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public double execute() {
    backEndTurtle.toggleVisibility();
    return 0;
  }
}
