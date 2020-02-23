package slogo.model.backEndInternal.commands.TurtleCommands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.BackEndTurtle;
import slogo.model.backEndInternal.commands.Command;

public class Home implements Command<Double> {

  BackEndTurtle backEndTurtle;

  Home(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  @Override
  public Double execute() {
    Coordinate currentPosition = backEndTurtle.getPosition();
    Double distance = Math.sqrt(Math.pow(currentPosition.getXVal(), 2) + Math.pow(currentPosition.getYVal(), 2));
    backEndTurtle.setPosition(new Coordinate());
    return distance;
  }
}
