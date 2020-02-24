package slogo.model.backEndInternal.commands;

public class Random implements Command<Double> {

  private double max;

 public Random(double v1) {
    this.max = v1;
  }

  @Override
  public Double execute() {
    return Math.floor(Math.random() * max);
  }
}
