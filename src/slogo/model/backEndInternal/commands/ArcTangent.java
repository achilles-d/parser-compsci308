package slogo.model.backEndInternal.commands;

public class ArcTangent implements Command<Double> {

  private double value;

  public ArcTangent(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return Math.atan(value);
  }
}
