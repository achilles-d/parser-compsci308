package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class ShowTurtle implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public ShowTurtle(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    backEndTurtle.toggleVisibility();
    return 1.0;
  }
}
