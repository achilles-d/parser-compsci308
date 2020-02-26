package slogo.model.backEndInternal.commands;

import java.util.List;

public class And implements Command<Double> {

  Double value1;
  Double value2;

  public And(Double v1, Double v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Double execute() {
    return (double) ((value1 != 0 && value2 != 0) ? 1 : 0);
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
