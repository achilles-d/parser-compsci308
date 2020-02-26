package slogo.model.backEndInternal.commands;

import java.util.List;

public class ArcTangent implements Command<Double> {

  private double value;

  public ArcTangent(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return Math.atan(value);
  }

  @Override
  public List<String> updateRawCommands() {
    return null;
  }

  @Override
  public Integer updateCounter() {
    return null;
  }
}
