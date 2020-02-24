package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class PenUp implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public PenUp(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    backEndTurtle.flipPen();
    return 0.0;
  }
}
