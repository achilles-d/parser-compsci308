package slogo.model.backEndInternal.commands.TurtleCommands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class SetPosition implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private int x;
  private int y;

  SetPosition(BackEndTurtle turtle, int val1, int val2) {
    this.backEndTurtle = turtle;
    this.x = val1;
    this.y = val2;
  }

  @Override
  public Double execute() {
    Coordinate currentPosition = backEndTurtle.getPosition();
    Coordinate newPosition = new Coordinate(x, y);
    backEndTurtle.setPosition(newPosition);

    return Math.sqrt(Math.pow(newPosition.getXVal() - currentPosition.getXVal(), 2) +
                      Math.pow(newPosition.getYVal() - currentPosition.getYVal(), 2));
  }
}
