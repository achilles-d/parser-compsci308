package slogo.model.commands;

public class Power implements Command<Double> {

  private Command cmdBase;
  private Command cmdExponent;


  /**
   * Command Constructor
   * @param cmd1 first input
   * @param cmd2 second input
   */
  public Power(Command cmd1, Command cmd2) {

    this.cmdBase=cmd1;
    this.cmdExponent=cmd2;
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    double base = (double) cmdBase.execute();
    double exponent = (double) cmdExponent.execute();
    return Math.pow(base, exponent);
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
