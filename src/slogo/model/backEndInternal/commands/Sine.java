package slogo.model.backEndInternal.commands;

public class Sine implements Command<Double> {

  private double value;

  public Sine(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return Math.sin(value);
  }
}
