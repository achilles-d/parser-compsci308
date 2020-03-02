package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class PenDown implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public PenDown(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    backEndTurtle.setPen(true);
    return 1.0;
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
