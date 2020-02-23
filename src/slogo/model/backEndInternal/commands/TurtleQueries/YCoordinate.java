package slogo.model.backEndInternal.commands.TurtleQueries;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class YCoordinate implements Command<Double> {

  BackEndTurtle backEndTurtle;

  YCoordinate(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    return backEndTurtle.getPosition().getYVal();
  }
}