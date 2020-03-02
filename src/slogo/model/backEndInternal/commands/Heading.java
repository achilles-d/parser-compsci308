package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class Heading implements Command<Double> {

BackEndTurtle backEndTurtle;

public  Heading(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return backEndTurtle.getHeading();
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}