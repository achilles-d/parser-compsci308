package slogo.model.commands;

public class Quotient implements Command<Double> {

  private  Command cmd1;
  private  Command cmd2;

  /**
   * Command Constructor
   * @param v1 first input
   * @param v2 second input
   */
  public Quotient(Command v1, Command v2) {
    this.cmd1=v1;
    this.cmd2=v2;

  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {

    return (double) (cmd1).execute() + (double) (cmd2).execute();
  }

  /**
   * Check if executable
   * @return Is it an executable command or not.
   */
  @Override
  public boolean isItExecutable() {
    return true;
  }

}
