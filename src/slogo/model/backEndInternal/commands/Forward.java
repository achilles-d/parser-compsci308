package slogo.model.backEndInternal.commands.s;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.commands.Command;

public class Forward implements Command<Double> {

  private Coordinate turtlePosition;
  private double movement;

  public Forward(Coordinate pos, double delta) {
    this.turtlePosition = pos;
    this.movement = delta;
  }

  @Override
  public Double execute() {
    turtlePosition.setYVal(turtlePosition.getYVal() + movement);
    return movement;
  }
}
