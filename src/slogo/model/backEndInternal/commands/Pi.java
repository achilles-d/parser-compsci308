package slogo.model.backEndInternal.commands;

public class Pi implements Command<Double> {

  public Pi(){;}
  @Override
  public Double execute() {
    return Math.PI;
  }
}
