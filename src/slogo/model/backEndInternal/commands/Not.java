package slogo.model.backEndInternal.commands;

public class Not implements Command<Integer> {

  Double value;

  public Not(Double v1) {
    this.value = v1;
  }

  @Override
  public Integer execute() {
    return value == 0 ? 1 : 0;
  }
}