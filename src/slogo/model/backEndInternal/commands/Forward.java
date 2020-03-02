package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class Forward implements Command<Double> {

  private BackEndTurtle myTurtle;
  private Command cmd;
  private double delta;

  public Forward(BackEndTurtle t, Command d) {
    this.myTurtle = t;
    this.cmd = d;
  }

  @Override
  public Double execute() {
    double angle = Math.toRadians(myTurtle.getHeading());
    delta = (double) cmd.execute();
    double deltaX = delta * Math.sin(angle);
    double deltaY = delta * Math.cos(angle);
    System.out.println("Xmoves " + deltaX);
    System.out.println("Ymoves " + deltaY);
    Coordinate currentPos = myTurtle.getPosition();
    Coordinate newPosition = new Coordinate(currentPos.getXVal() + deltaX, currentPos.getYVal() + deltaY);
    System.out.println(newPosition);
    myTurtle.setPosition(newPosition);
    return delta;
  }

}
