package slogo.controller;

import java.awt.Point;
import java.util.List;
import slogo.model.Line;
import slogo.model.Variable;

public interface Controller {

  public void toggleVisibility();

  public String displayError(Exception ex);

  public Point getTurtlePosition();

  public void parseCode(String code) throws Exception;

  public List<String> getCommandHistory();

  public double getHeading();

  public List<Line> getLines();

  public Variable getVariable(String varName);

  public List<String> getAllVariables();

}
