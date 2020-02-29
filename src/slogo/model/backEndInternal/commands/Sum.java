package slogo.model.backEndInternal.commands;

import java.util.List;

public class Sum implements Command<Double> {

  private double value1;
  private double value2;

  public Sum(Command v1, Command v2) {
    this.value1 = (double) (v1).execute();
    this.value2 = (double) (v2).execute();
  }

  @Override
  public Double execute() {
    return value1 + value2;
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
