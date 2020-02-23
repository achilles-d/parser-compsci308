package slogo.model.backEndInternal.commands.TurtleQueries;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class Heading implements Command<Double> {

  BackEndTurtle backEndTurtle;

  Heading(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return backEndTurtle.getHeading();
  }
}