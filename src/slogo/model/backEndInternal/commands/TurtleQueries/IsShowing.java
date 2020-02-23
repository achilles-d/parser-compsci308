package slogo.model.backEndInternal.commands.TurtleQueries;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class IsShowing implements Command<Integer> {

  BackEndTurtle backEndTurtle;

  IsShowing(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Integer execute() {
    return backEndTurtle.getVisibility() ? 1 : 0;
  }
}