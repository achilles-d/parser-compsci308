package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

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
    System.out.println("got here");
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
  public List<String> updateRawCommands() {
    return null;
  }

  @Override
  public Integer updateCounter() {
    return null;
  }
}
