package slogo.model.backEndInternal.commands.BooleanOperations;

import slogo.model.backEndInternal.commands.Command;

public class Equal<T extends Comparable<T>> implements Command<Integer> {

  T value1;
  T value2;

  Equal(T v1, T v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Integer execute() {
    return value1.compareTo(value2) == 0 ? 1 : 0;
  }
}
