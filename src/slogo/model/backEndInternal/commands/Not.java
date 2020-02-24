package slogo.model.backEndInternal.commands;

public class Not implements Command<Double> {

  Double value;

  public Not(Double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return (double) (value == 0 ? 1 : 0);
  }
}
