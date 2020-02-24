package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class IsPenDown implements Command<Double> {

  BackEndTurtle backEndTurtle;

 public IsPenDown(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return backEndTurtle.getPenStatus() ? 0 : 1;
  }
}
