package slogo.model.commands;

public class NaturalLog implements Command<Double> {

    private final Command cmd;

    /**
     * command constructor
     * @param cmd constant
     */
    public NaturalLog(Command cmd) {
    this.cmd=cmd;

    }

    /**
     * Execution logic
     * @return Double value argument
     */
  @Override
  public Double execute() {
      double value = (double) cmd.execute();
     return Math.log(value);
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
