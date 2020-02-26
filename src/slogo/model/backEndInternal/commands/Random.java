package slogo.model.backEndInternal.commands;

import java.util.List;

public class Random implements Command<Double> {

  private double max;

 public Random(double v1) {
    this.max = v1;
  }

  @Override
  public Double execute() {
    return Math.floor(Math.random() * max);
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
