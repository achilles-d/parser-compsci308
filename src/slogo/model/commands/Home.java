package slogo.model.commands;

import slogo.model.turtle.Coordinate;
import slogo.model.turtle.BackEndTurtle;

public class Home implements Command<Double> {

  BackEndTurtle backEndTurtle;

  public Home(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    Coordinate currentPosition = backEndTurtle.getPosition();
    Double distance = Math.sqrt(Math.pow(currentPosition.getXVal(), 2) + Math.pow(currentPosition.getYVal(), 2));
    backEndTurtle.setPosition(new Coordinate());
    backEndTurtle.setHeading(0);
    return distance;
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
