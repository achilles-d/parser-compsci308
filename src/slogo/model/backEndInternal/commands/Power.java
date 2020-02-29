package slogo.model.backEndInternal.commands;

import java.util.List;

public class Power implements Command<Double> {

  private double base;
  private double exponent;

  public Power(Double v1, Double v2) {
    this.base = v1;
    this.exponent = v2;
  }

  @Override
  public Double execute() {
    return Math.pow(base, exponent);
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
