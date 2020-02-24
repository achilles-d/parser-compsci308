package slogo.model.backEndInternal.commands;

public class Pi implements Command<Double> {

  public Pi(){;}
  @Override
  public double execute() {
    return Math.PI;
  }
}
