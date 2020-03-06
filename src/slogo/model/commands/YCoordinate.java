package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class YCoordinate implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public YCoordinate(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return backEndTurtle.getPosition().getYVal();
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }

}