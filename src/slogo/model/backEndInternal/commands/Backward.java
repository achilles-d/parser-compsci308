package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.BackEndTurtle;

public class Backward implements Command<Double> {

  private BackEndTurtle myTurtle;
  private double delta;

  public Backward(BackEndTurtle t, double d) {
    this.myTurtle = t;
    this.delta = d;
  }

  @Override
  public Double execute() {
    double angle = Math.toRadians(myTurtle.getHeading());
    double deltaX = delta * Math.sin(angle);
    double deltaY = delta * Math.cos(angle);
    Coordinate currentPos = myTurtle.getPosition();
    Coordinate newPosition = new Coordinate(currentPos.getXVal() - deltaX, currentPos.getYVal() - deltaY);
    myTurtle.setPosition(newPosition);
    return delta;
  }
}
