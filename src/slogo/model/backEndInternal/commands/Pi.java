package slogo.model.backEndInternal.commands;

import java.util.List;

public class Pi implements Command<Double> {

  public Pi(){;}
  @Override
  public Double execute() {
    return Math.PI;
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
