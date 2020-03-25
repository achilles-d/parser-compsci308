package slogo.model.commands;

import slogo.model.turtle.BackEndTurtle;

public class Left implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double degrees;
  private Command cmd;

    /**
     * Command
     * @param turtle backend turtle to do on
     * @param cmd constant to use
     */
 public Left(BackEndTurtle turtle, Command cmd) {
    this.backEndTurtle = turtle;
    this.cmd = cmd;

  }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Double execute() {
     degrees= (double) cmd.execute();
     backEndTurtle.setHeading(backEndTurtle.getHeading() - degrees);
     return degrees;
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
