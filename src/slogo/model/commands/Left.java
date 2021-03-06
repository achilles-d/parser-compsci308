package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class Left implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;
  private Command cmd;

 public Left(BackEndTurtle turtle, Command cmd) {
    this.backEndTurtle = turtle;
    this.cmd = cmd;

  }

  @Override
  public Double execute() {
     degrees= (double) cmd.execute();
    backEndTurtle.setHeading(backEndTurtle.getHeading() - degrees);
    return degrees;
  }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
