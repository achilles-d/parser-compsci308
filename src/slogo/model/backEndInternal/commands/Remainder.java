package slogo.model.backEndInternal.commands;

import java.util.List;

public class Remainder implements Command<Double> {

  private Command cmd1;
  private Command cmd2;

  public Remainder(Command v1, Command v2) {
    this.cmd1 = v1;
    this.cmd2 = v2;

  }

  @Override
  public Double execute() {

    return (double) (cmd1).execute() % (double) (cmd2).execute();
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
