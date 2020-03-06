package slogo.model.commands;

public class Sine implements Command<Double> {

  private Command cmd;

  public Sine(Command cmd) {
    this.cmd=cmd;

  }

  @Override
  public Double execute() {
    double value = (double) cmd.execute();
    return Math.sin(value);
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
