package slogo.model.commands;

import slogo.model.turtle.Coordinate;
import slogo.model.turtle.BackEndTurtle;

public class Home implements Command<Double> {

  BackEndTurtle backEndTurtle;

  /**
   * Command Constructor
   * @param myTurtle which backend turtle to apply to
   */
  public Home(BackEndTurtle myTurtle) {
    this.backEndTurtle = myTurtle;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    Coordinate currentPosition = backEndTurtle.getPosition();
    Double distance = Math.sqrt(Math.pow(currentPosition.getXVal(), 2) + Math.pow(currentPosition.getYVal(), 2));
    backEndTurtle.setPosition(new Coordinate());
    backEndTurtle.setHeading(0);
    return distance;
  }

  /**
   * Check if executable
   * @return Is it an executable command or not.
   */
  @Override
  public boolean isItExecutable() {
    return true;
  }
}
