package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;

public class Left implements Command<Double> {

  private Coordinate turtlePosition;
  private double movement;

  Left(Coordinate pos, double delta) {
    this.turtlePosition = pos;
    this.movement = delta;
  }

  @Override
  public Double execute() {
    turtlePosition.setXVal(turtlePosition.getXVal() - movement);
    return movement;
  }
}
