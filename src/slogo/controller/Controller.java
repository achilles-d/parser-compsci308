package slogo.controller;

import java.awt.Point;
import java.util.List;
import slogo.model.Variable;

public interface Controller {

  public void updateViewTurtlePosition();

  public void updateTrails();

  public void toggleVisibility();

  public void clearScreen();

  public void displayError(Exception ex);

  public Point getTurtlePosition();

  public double getTurtleHeading();

  public void parseCode(String code);

  public List<String> getCommandHistory();

  public double getHeading();

  public List<Point> getLines();

  public Variable getVariable(String varName);

  public List<String> getAllVariables();

}
