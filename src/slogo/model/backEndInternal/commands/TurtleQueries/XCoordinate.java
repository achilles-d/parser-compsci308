package slogo.model.backEndInternal.commands.TurtleQueries;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class XCoordinate implements Command<Double> {

  BackEndTurtle backEndTurtle;

  XCoordinate(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return backEndTurtle.getPosition().getXVal();
  }
}
