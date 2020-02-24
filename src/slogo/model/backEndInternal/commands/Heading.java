package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

public class Heading implements Command<Double> {

BackEndTurtle backEndTurtle;

public  Heading(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public double execute() {
    return backEndTurtle.getHeading();
  }
}