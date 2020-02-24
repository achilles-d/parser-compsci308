package slogo.model.backEndInternal.commands;

public class Minus implements Command<Double> {

  private double value;

 public Minus(double v1) {
    this.value = v1;
  }

  @Override
  public double execute() {
    return value * -1;
  }
}
