package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;

public class Right implements Command<Double> {

  private Coordinate turtlePosition;
  private double movement;

  Right(Coordinate pos, double delta) {
    this.turtlePosition = pos;
    this.movement = delta;
  }

  @Override
  public Double execute() {
    turtlePosition.setXVal(turtlePosition.getXVal() + movement);
    return movement;
  }
}
