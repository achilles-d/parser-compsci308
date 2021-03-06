package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class IsPenDown implements Command<Double> {

  BackEndTurtle backEndTurtle;

 public IsPenDown(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return (double) (backEndTurtle.getPenStatus() ? 0 : 1);
  }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
