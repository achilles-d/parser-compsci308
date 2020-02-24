package slogo.model.backEndInternal.commands;

public class NaturalLog implements Command<Double> {

  private double value;

 public NaturalLog(double v1) {
    this.value = v1;
  }

  @Override
  public double execute() {
    return Math.log(value);
  }
}
