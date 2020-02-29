package slogo.model.backEndInternal.commands;

import java.util.List;

public class Random implements Command<Double> {

    private Command cmd;

 public Random(Command cmd) {
     this.cmd=cmd;
  }

  @Override
  public Double execute() {

      double max = (double) cmd.execute();
     return Math.floor(Math.random() * max);
  }

    @Override
    public List<String> updateRawCommands() {
        return null;
    }

    @Override
    public Integer updateCounter() {
        return null;
    }
}
