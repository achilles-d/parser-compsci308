package slogo.model.backEndInternal.commands;

import java.util.List;

public class Sine implements Command<Double> {

  private Command cmd;

  public Sine(Command cmd) {
    this.cmd=cmd;

  }

  @Override
  public Double execute() {
    double value = (double) cmd.execute();
    return Math.sin(value);
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
