package slogo.model.commands;

public class Random implements Command<Double> {

    private Command cmd;

    /**
     * Command Constructor
     * @param cmd first input
     */
    public Random(Command cmd) {
     this.cmd=cmd;
  }

    /**
     * Execution logic
     * @return Double value argument
     */
  @Override
  public Double execute() {
      double max = (double) cmd.execute();
      return Math.floor(Math.random() * max);
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
