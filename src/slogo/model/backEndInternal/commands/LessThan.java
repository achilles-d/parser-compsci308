package slogo.model.backEndInternal.commands;

public class LessThan<T extends Comparable<T>> implements Command<Double> {

  T value1;
  T value2;

  public LessThan(T v1, T v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Double execute() {
    return (double) (value1.compareTo(value2) < 0 ? 1 : 0);
  }
}
