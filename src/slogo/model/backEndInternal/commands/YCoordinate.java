package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

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