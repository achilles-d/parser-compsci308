package slogo.model.backEndInternal.commands;

import java.util.List;

public class Tangent implements Command<Double> {

  private Command cmd;

  public Tangent(Command cmd) {
    this.cmd=cmd;

  }

  @Override
  public Double execute() {
    double value = (double) cmd.execute();
    return Math.tan(value);
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
