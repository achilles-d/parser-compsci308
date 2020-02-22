package slogo.model.backEndInternal.commands.TurtleCommands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.commands.Command;

public class Backward implements Command<Double> {

  private Coordinate turtlePosition;
  private double movement;

  Backward(Coordinate pos, double delta) {
    this.turtlePosition = pos;
    this.movement = delta;
  }


  @Override
  public Double execute() {
    turtlePosition.setYVal(turtlePosition.getYVal() - movement);
    return movement;
  }
}
