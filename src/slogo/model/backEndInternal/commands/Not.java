package slogo.model.backEndInternal.commands;

import java.util.List;

public class Not implements Command<Double> {

  Double value;

  public Not(Double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return (double) (value == 0 ? 1 : 0);
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
