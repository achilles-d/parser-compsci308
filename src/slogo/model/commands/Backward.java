package slogo.model.commands;

import slogo.model.turtle.Coordinate;
import slogo.model.turtle.BackEndTurtle;

public class Backward implements Command<Double> {

  private BackEndTurtle myTurtle;
  private double delta;

  private Command cmd;
  public Backward(BackEndTurtle t,Command d) {
    this.myTurtle = t;
    this.cmd = d;
  }

  @Override
  public Double execute() {
    delta=(double) cmd.execute();
    double angle = Math.toRadians(myTurtle.getHeading());
    double deltaX = delta * Math.sin(angle);
    double deltaY = delta * Math.cos(angle);
    Coordinate currentPos = myTurtle.getPosition();
    Coordinate newPosition = new Coordinate(currentPos.getXVal() - deltaX, currentPos.getYVal() - deltaY);
    myTurtle.setPosition(newPosition);
    return delta;
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
