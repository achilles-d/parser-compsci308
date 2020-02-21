package slogo.model.backEndInternal.commands;

import slogo.model.Coordinate;

public class Forward implements Command{

  @Override
  public Coordinate execute(Coordinate current, int delta) {
    return new Coordinate(current.getXVal(), current.getYVal()+delta);
  }
}
