package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class XCoordinate implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public XCoordinate(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return backEndTurtle.getPosition().getXVal();
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }

}
