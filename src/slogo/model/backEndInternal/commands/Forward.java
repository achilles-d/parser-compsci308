package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.BackEndTurtle;

public class Forward implements Command<Double> {

  private BackEndTurtle myTurtle;
  private double delta;

  public Forward(BackEndTurtle t, double d) {
    this.myTurtle = t;
    this.delta = d;
  }

  @Override
  public Double execute() {
    double angle = myTurtle.getHeading();
    double deltaX = delta * Math.sin(angle);
    double deltaY = delta * Math.cos(angle);
    Coordinate currentPos = myTurtle.getPosition();
    Coordinate newPosition = new Coordinate(currentPos.getXVal() + deltaX, currentPos.getYVal() + deltaY);
    System.out.println(newPosition);
    myTurtle.setPosition(newPosition);
    return delta;
  }
}
