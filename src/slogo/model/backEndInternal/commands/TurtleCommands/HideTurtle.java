package slogo.model.backEndInternal.commands.TurtleCommands;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class HideTurtle implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  HideTurtle(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Integer execute() {
    backEndTurtle.toggleVisibility();
    return 0;
  }
}
