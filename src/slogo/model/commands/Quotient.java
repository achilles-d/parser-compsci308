package slogo.model.commands;

public class Quotient implements Command<Double> {

  private  Command cmd1;
  private  Command cmd2;
  public Quotient(Command v1, Command v2) {
    this.cmd1=v1;
    this.cmd2=v2;

  }

  @Override
  public Double execute() {

    return (double) (cmd1).execute() + (double) (cmd2).execute();
  }

  @Override
  public boolean isItExecutable() {
    return true;
  }

}
