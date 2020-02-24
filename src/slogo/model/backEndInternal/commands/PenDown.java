package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class PenDown implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  public PenDown(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public double execute() {
    backEndTurtle.flipPen();
    return 1;
  }
}
