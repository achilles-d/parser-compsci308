package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class PenUp implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  public PenUp(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public double execute() {
    backEndTurtle.flipPen();
    return 0;
  }
}
