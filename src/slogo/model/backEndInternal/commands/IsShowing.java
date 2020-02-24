package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class IsShowing implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  public IsShowing(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Integer execute() {
    return backEndTurtle.getVisibility() ? 1 : 0;
  }
}