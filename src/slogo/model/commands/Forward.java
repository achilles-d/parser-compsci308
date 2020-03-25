package slogo.model.commands;

import slogo.model.turtle.Coordinate;
import slogo.model.turtle.BackEndTurtle;

public class Forward implements Command<Double> {

  private BackEndTurtle myTurtle;
  private Command cmd;
  private double delta;

  /**
   * Command constructor
   * @param t backendturtle to apply to
   * @param d command
   */
  public Forward(BackEndTurtle t, Command d) {
    this.myTurtle = t;
    this.cmd = d;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    double angle = Math.toRadians(myTurtle.getHeading());
    delta = (double) cmd.execute();
    double deltaX = delta * Math.sin(angle);
    double deltaY = delta * Math.cos(angle);
    Coordinate currentPos = myTurtle.getPosition();
    Coordinate newPosition = new Coordinate(currentPos.getXVal() + deltaX, currentPos.getYVal() + deltaY);
    System.out.println(newPosition);
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
