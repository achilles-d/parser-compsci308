package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class IsShowing implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public IsShowing(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return (double) (backEndTurtle.getVisibility() ? 1 : 0);
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}