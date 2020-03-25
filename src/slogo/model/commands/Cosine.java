package slogo.model.commands;

public class Cosine implements Command<Double> {

  private Command cmd;

  /**
   * Command Constructor
   * @param cmd input to cosine
   */
  public Cosine(Command cmd) {
    this.cmd=cmd;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    double value = (double) cmd.execute();
    return Math.cos(value);
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
