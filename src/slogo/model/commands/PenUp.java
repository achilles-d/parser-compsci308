package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class PenUp implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public PenUp(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    backEndTurtle.setPen(false);
    return 0.0;
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
