package slogo.model.backEndInternal.commands;

import java.util.List;

public class Power implements Command<Double> {

  private Command cmdBase;
  private Command cmdExponent;

  public Power(Command cmd1, Command cmd2) {

    this.cmdBase=cmd1;
    this.cmdExponent=cmd2;
  }

  @Override
  public Double execute() {
    double base = (double) cmdBase.execute();
    double exponent = (double) cmdExponent.execute();
    return Math.pow(base, exponent);
  }
  @Override
  public boolean isItExecutable() {
    return true;
  }
}
