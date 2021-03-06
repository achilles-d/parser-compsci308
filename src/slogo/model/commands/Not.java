package slogo.model.commands;

public class Not implements Command<Double> {

  private Command cmd;

  public Not(Command cmd) {
    this.cmd=cmd;

  }

  @Override
  public Double execute() {
    Double value = (Double) cmd.execute();
    return (double) (value == 0 ? 1 : 0);
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }

}
