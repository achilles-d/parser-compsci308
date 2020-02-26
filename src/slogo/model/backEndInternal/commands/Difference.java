package slogo.model.backEndInternal.commands;

import java.util.List;

public class Difference implements Command<Double> {

  private double value1;
  private double value2;

 public Difference(double v1, double v2) {
    this.value1 = v1;
    this.value2 = v2;
  }

  @Override
  public Double execute() {
    return value1 - value2;
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
