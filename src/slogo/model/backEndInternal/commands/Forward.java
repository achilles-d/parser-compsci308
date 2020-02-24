package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;

public class Forward implements Command<Double> {

  private Coordinate turtlePosition;
  private double movement;

  public Forward(Coordinate pos, double delta) {
    this.turtlePosition = pos;
    this.movement = delta;
  }

  @Override
  public double execute() {
    turtlePosition.setYVal(turtlePosition.getYVal() + movement);
    return movement;
  }
}
