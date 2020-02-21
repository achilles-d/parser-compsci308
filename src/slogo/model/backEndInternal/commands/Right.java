package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;

public class Right implements Command {

  @Override
  public Coordinate execute(Coordinate current, int delta) {
    return new Coordinate(current.getXVal()+delta, current.getYVal());
  }
}
