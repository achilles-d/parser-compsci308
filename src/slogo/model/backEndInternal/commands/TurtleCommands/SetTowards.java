package slogo.model.backEndInternal.commands.TurtleCommands;

import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class SetTowards implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private int x;
  private int y;

  SetTowards(BackEndTurtle myTurtle, int val1, int val2) {
    this.backEndTurtle = myTurtle;
    this.x = val1;
    this.y = val2;
  }

  @Override
  public Double execute() {
    double newHeading = Math.toDegrees(Math.atan2(y, x));
    backEndTurtle.setHeading(newHeading);
    return newHeading;
  }
}
