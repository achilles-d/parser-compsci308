package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

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

}
