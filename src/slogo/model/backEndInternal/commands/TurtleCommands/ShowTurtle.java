package slogo.model.backEndInternal.commands.TurtleCommands;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class ShowTurtle implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  ShowTurtle(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Integer execute() {
    backEndTurtle.toggleVisibility();
    return 1;
  }
}
