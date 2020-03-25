package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class SetTowards implements Command<Double> {

  private BackEndTurtle backEndTurtle;
    private Command cmd1;
  private Command cmd2;

    /**
     * command constructor
     * @param myTurtle backend turtle used to change
     * @param cmd1 constant passed for x
     * @param cmd2 constant passed for y
     */
 public SetTowards(BackEndTurtle myTurtle, Command cmd1, Command cmd2) {
    this.backEndTurtle = myTurtle;
    this.cmd1 = cmd1;
    this.cmd2 = cmd2;
  }

    /**
     * Execution logic
     * @return Double value argument
     */
  @Override
  public Double execute() {
     double x = (double) cmd1.execute();
     double y = (double) cmd2.execute();
    double newHeading = Math.toDegrees(Math.atan2(y, x));
    backEndTurtle.setHeading(newHeading);
    return newHeading;
  }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    public boolean isItExecutable() {
        return true;
    }
}
