package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;
import slogo.model.backEndInternal.BackEndTurtle;

import java.util.List;

public class SetPosition implements Command<Double> {

  private BackEndTurtle backEndTurtle;
  private double x;
  private double y;

 public SetPosition(BackEndTurtle myTurtle, double val1, double val2) {
    this.backEndTurtle = myTurtle;
    this.x = val1;
    this.y = val2;
  }

  @Override
  public Double execute() {
    Coordinate currentPosition = backEndTurtle.getPosition();
    Coordinate newPosition = new Coordinate(x, y);
    backEndTurtle.setPosition(newPosition);

    return Math.sqrt(Math.pow(newPosition.getXVal() - currentPosition.getXVal(), 2) +
                      Math.pow(newPosition.getYVal() - currentPosition.getYVal(), 2));
  }

    @Override
    public List<String> updateRawCommands() {
        return null;
    }

    @Override
    public Integer updateCounter() {
        return null;
    }
}
