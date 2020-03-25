package slogo.model.commands;

public class Minus implements Command<Double> {

  private Command cmd;

    /**
     * command constructor
     * @param cmd constant
     */
    public Minus(Command cmd) {
    this.cmd = cmd;
  }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Double execute() {
    return (double)cmd.execute() * -1;
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
