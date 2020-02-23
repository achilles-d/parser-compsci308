package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class PenUp implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  public PenUp(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Integer execute() {
    backEndTurtle.flipPen();
    return 0;
  }
}
