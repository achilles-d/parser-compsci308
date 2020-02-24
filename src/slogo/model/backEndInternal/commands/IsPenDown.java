package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class IsPenDown implements Command<Integer> {

  BackEndTurtle backEndTurtle;

 public IsPenDown(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public double execute() {
    return backEndTurtle.getPenStatus() ? 0 : 1;
  }
}
