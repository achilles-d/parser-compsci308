package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class ShowTurtle implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  public ShowTurtle(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Integer execute() {
    backEndTurtle.toggleVisibility();
    return 1;
  }
}
