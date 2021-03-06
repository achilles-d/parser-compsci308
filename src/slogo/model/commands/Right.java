package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class Right implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;
  private Command cmd;

  public Right(BackEndTurtle turtle,Command angle) {
    this.backEndTurtle = turtle;
    this.cmd = angle;
  }

  @Override
  public Double execute() {
    degrees= (double) cmd.execute();
    backEndTurtle.setHeading(backEndTurtle.getHeading() + degrees);
    return degrees;
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }

}
