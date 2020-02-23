package slogo.model.backEndInternal.commands.TurtleCommands;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class PenUp implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  PenUp(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Integer execute() {
    backEndTurtle.flipPen();
    return 0;
  }
}
