package slogo.model.backEndInternal.commands.TurtleQueries;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class IsPenDown implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  IsPenDown(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Integer execute() {
    return backEndTurtle.getPenStatus() ? 0 : 1;
  }
}
