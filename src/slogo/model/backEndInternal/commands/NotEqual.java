package slogo.model.backEndInternal.commands;

import java.util.List;

public class NotEqual<T extends Comparable<T>> implements Command<Double> {
  private Command<T> cmd2;
  private Command<T> cmd1;
  T value1;
  T value2;

  public NotEqual(Command<T> cmd1, Command<T> cmd2) {
    this.cmd1=cmd1;
    this.cmd2=cmd2;
  }

  @Override
  public Double execute() {
    value1= cmd1.execute();
    value2= cmd2.execute();

    return (double) (value1.equals(value2) ? 0 : 1);
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
