package slogo.model.backEndInternal.commands;

public class Quotient implements Command<Double> {

  private double value1;
  private double value2;

  Quotient(double v1, double v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Double execute() {
    return value1 / value2;
  }

}
