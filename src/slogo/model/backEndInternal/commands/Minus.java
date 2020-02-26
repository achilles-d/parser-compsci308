package slogo.model.backEndInternal.commands;

import java.util.List;

public class Minus implements Command<Double> {

  private double value;

 public Minus(double v1) {
    this.value = v1;
  }

  @Override
  public Double execute() {
    return value * -1;
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
