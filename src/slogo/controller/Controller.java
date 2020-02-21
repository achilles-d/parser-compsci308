package slogo.controller;

import java.awt.Point;
import java.util.List;
import slogo.model.Variable;

public interface Controller {

  public void updateViewTurtlePosition();

  public void updateTrails();

  public void toggleVisibility();

  public void clearScreen();

  public void displayError();

  public Point getTurtlePosition();

  public double getTurtleHeading();

  public void parseCode();

  public List<String> getCommandHistory();

  public double getHeading();

  public List<Point> getLines();

  public Variable getVariable();

}
