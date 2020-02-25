package slogo.model.backEndInternal.commands;

public class GreaterThan<T extends Comparable<T>> implements Command<Double> {

  T value1;
  T value2;

  public GreaterThan(T v1, T v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Double execute() {
    return value2.compareTo(value1) > 0 ? 1.0 : 0.0;
  }
}
