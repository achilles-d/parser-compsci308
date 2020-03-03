package slogo.model.backEndInternal.commands;

import java.util.List;

public class ArcTangent implements Command<Double> {

  private final Command cmd;
  private double value;

  public ArcTangent(Command cmd) {
    this.cmd = cmd;

  }

  @Override
  public Double execute() {
    value = (double) cmd.execute();
    return Math.atan(value);
  }


  @Override
  public boolean isItExecutable() {
    return true;
  }
}
