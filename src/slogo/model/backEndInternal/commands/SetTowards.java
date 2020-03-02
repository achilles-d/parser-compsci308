package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class SetTowards implements Command<Double> {

  private BackEndTurtle backEndTurtle;
    private Command cmd1;
  private Command cmd2;


 public SetTowards(BackEndTurtle myTurtle, Command cmd1, Command cmd2) {
    this.backEndTurtle = myTurtle;
    this.cmd1 = cmd1;
    this.cmd2 = cmd2;
  }

  @Override
  public Double execute() {
     double x = (double) cmd1.execute();
     double y = (double) cmd2.execute();
    double newHeading = Math.toDegrees(Math.atan2(y, x));
    backEndTurtle.setHeading(newHeading);
    return newHeading;
  }

}
