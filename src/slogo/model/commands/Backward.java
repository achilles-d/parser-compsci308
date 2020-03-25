package slogo.model.commands;

import slogo.model.turtle.Coordinate;
import slogo.model.turtle.BackEndTurtle;

public class Backward implements Command<Double> {

  private BackEndTurtle myTurtle;
  private double delta;

  private Command cmd;

  /**
   * Command Constructor
   * @param t backend turtle to change
   * @param d command to apply
   */
  public Backward(BackEndTurtle t,Command d) {
    this.myTurtle = t;
    this.cmd = d;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
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

  /**
   * Check if executable
   * @return Is it an executable command or not.
   */
  @Override
  public boolean isItExecutable() {
    return true;
  }
}
