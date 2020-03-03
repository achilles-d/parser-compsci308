package slogo.model.backEndInternal.commands;

import java.util.List;

public class NaturalLog implements Command<Double> {

    private final Command cmd;

    public NaturalLog(Command cmd) {
    this.cmd=cmd;

  }

  @Override
  public Double execute() {
      double value = (double) cmd.execute();
     return Math.log(value);
  }


  @Override
  public boolean isItExecutable() {
        return true;
    }
}
