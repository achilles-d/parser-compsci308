package slogo.model.backEndInternal.commands;

public class Tangent implements Command<Double> {

  private double value;

  public Tangent(double v1) {
    this.value = v1;
  }

  @Override
  public double execute() {
    return Math.tan(value);
  }
}
