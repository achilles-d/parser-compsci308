package slogo.model.commands;

public class And implements Command<Double> {

  private  Command cmd2;
  private Command cmd1;

  /**
   * Command Constructor
   * @param cmd1 first input
   * @param cmd2 second input
   */
  public And(Command cmd1, Command cmd2) {
      this.cmd1=cmd1;
      this.cmd2=cmd2;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    Double value1 = (Double) cmd1.execute();
    Double value2 = (Double) cmd2.execute();
    return (double) ((value1 != 0 && value2 != 0) ? 1 : 0);
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
