package slogo.model.backEndInternal.commands;

import java.util.List;

public class Equal<T extends Comparable<T>> implements Command<Double> {

  T value1;
  T value2;

  public Equal(T v1, T v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Double execute() {
    return (double) (value1.compareTo(value2) == 0 ? 1 : 0);
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
