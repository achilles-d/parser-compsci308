package slogo.model.commands;

public class ArcTangent implements Command<Double> {

  private final Command cmd;
  private double value;

  /**
   * Command Constructor
   * @param cmd first input
   */
  public ArcTangent(Command cmd) {
    this.cmd = cmd;

  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    value = (double) cmd.execute();
    return Math.atan(value);
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
