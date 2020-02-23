package slogo.model.backEndInternal.commands;

public class Cosine implements Command<Double> {

  private double value;

  public Cosine(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return Math.cos(value);
  }
}
