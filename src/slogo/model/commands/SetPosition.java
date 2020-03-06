package slogo.model.commands;

import slogo.model.turtle.Coordinate;
import slogo.model.turtle.BackEndTurtle;

public class SetPosition implements Command<Double> {

  private BackEndTurtle backEndTurtle;
    private Command cmd1;
  private Command cmd2;


 public SetPosition(BackEndTurtle myTurtle, Command cmd1, Command cmd2) {
    this.backEndTurtle = myTurtle;
    this.cmd1 =cmd1;
    this.cmd2= cmd2;
  }

  @Override
  public Double execute() {
     double x = (double) cmd1.execute();
     double y = (double) cmd2.execute();
     Coordinate currentPosition = backEndTurtle.getPosition();
    Coordinate newPosition = new Coordinate(x, y);
    backEndTurtle.setPosition(newPosition);

    return Math.sqrt(Math.pow(newPosition.getXVal() - currentPosition.getXVal(), 2) +
                      Math.pow(newPosition.getYVal() - currentPosition.getYVal(), 2));
  }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
