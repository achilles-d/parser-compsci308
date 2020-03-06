package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class ShowTurtle implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public ShowTurtle(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    backEndTurtle.setVisibility(true);
    return 1.0;
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
