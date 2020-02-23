package slogo.model.backEndInternal.commands.BooleanOperations;

import slogo.model.backEndInternal.commands.Command;

public class Not implements Command<Integer> {

  Double value;

  Not(Double v1) {
    this.value = v1;
  }

  @Override
  public Integer execute() {
    return value == 0 ? 1 : 0;
  }
}
