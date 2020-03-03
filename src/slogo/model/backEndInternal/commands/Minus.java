package slogo.model.backEndInternal.commands;

import java.util.List;

public class Minus implements Command<Double> {

  private Command cmd;

 public Minus(Command cmd) {
    this.cmd = cmd;
  }

    @Override
    public Double execute() {
    return (double)cmd.execute() * -1;
  }

    @Override
    public boolean isItExecutable() {
        return true;
    }

}
