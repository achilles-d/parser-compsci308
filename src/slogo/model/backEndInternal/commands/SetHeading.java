package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class SetHeading implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;
  private Command cmd;

  public SetHeading(BackEndTurtle turtle, Command cmd) {
    this.backEndTurtle = turtle;
    this.cmd = cmd;
  }

  @Override
  public Double execute() {
    degrees= (double) cmd.execute();
    Double calcDegrees = Math.abs(backEndTurtle.getHeading() - degrees);
    backEndTurtle.setHeading(degrees);
    return calcDegrees;
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
