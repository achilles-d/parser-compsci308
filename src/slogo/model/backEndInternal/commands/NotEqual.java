package slogo.model.backEndInternal.commands;

public class NotEqual<T extends Comparable<T>> implements Command<Double> {

  T value1;
  T value2;

  public NotEqual(T v1, T v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Double execute() {
    return value1.equals(value2) ? 0.0 : 1.0;
  }
}
