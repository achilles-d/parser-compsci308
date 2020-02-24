package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class PenDown implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public PenDown(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    backEndTurtle.flipPen();
    return 1.0;
  }
}
