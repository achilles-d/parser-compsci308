package slogo.model.commands;

public class Pi implements Command<Double> {

  @Override
  public Double execute() {
    return Math.PI;
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }

}
