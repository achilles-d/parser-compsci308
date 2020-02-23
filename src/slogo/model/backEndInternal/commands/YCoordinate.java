package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class YCoordinate implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public YCoordinate(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return backEndTurtle.getPosition().getYVal();
  }
}