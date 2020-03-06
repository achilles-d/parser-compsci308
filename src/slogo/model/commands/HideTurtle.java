package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class HideTurtle implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public HideTurtle(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    backEndTurtle.setVisibility(false);
    return (double) 0;
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
