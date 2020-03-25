package slogo.model.commands;

public class Not implements Command<Double> {

  private Command cmd;


  /**
   * Command Constructor
   * @param cmd user input
   */
  public Not(Command cmd) {
    this.cmd=cmd;

  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    Double value = (Double) cmd.execute();
    return (double) (value == 0 ? 1 : 0);
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
