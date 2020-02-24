package slogo.model.backEndInternal.commands;

public class NaturalLog implements Command<Double> {

  private double value;

 public NaturalLog(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return Math.log(value);
  }
}
