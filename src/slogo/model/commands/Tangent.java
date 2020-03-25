package slogo.model.commands;

public class Tangent implements Command<Double> {

  private Command cmd;

  /**
   * command constructor
   * @param cmd constant
   */
  public Tangent(Command cmd) {
    this.cmd=cmd;

  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    double value = (double) cmd.execute();
    return Math.tan(value);
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
