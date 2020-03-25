package slogo.model.commands;

public class Pi implements Command<Double> {

  /**
   * command constructor
   */
  public Pi(){
  }

  /**
   * Execution logic
   * @return Double value argument
   */
  @Override
  public Double execute() {
    return Math.PI;
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
