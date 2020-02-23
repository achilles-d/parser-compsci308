package slogo.model.backEndInternal.commands.BooleanOperations;

import slogo.model.backEndInternal.commands.Command;

public class And implements Command<Integer> {

  Double value1;
  Double value2;

  And(Double v1, Double v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Integer execute() {
    return (value1 != 0 && value2 != 0 ) ? 1 : 0;
  }
}
