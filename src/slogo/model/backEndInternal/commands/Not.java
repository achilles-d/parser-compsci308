package slogo.model.backEndInternal.commands;

import java.util.List;

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

}
