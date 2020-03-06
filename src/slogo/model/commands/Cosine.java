package slogo.model.commands;

public class Cosine implements Command<Double> {

  private Command cmd;

  public Cosine(Command cmd) {
    this.cmd=cmd;
  }

  @Override
  public Double execute() {
    double value = (double) cmd.execute();
    return Math.cos(value);
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
