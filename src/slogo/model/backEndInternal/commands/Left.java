package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class Left implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;

 public Left(BackEndTurtle turtle, double angle) {
    this.backEndTurtle = turtle;
    this.degrees = angle;
  }

  @Override
  public Double execute() {
    backEndTurtle.setHeading(backEndTurtle.getHeading() - degrees);
    return degrees;
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
