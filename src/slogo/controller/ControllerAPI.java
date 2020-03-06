package slogo.controller;

import java.awt.Point;
import java.util.List;

import slogo.model.turtle.Line;
import slogo.model.turtle.UserVariable;

public interface ControllerAPI {

  public String displayError(Exception ex);

  public Point getTurtlePosition();

  public void parseCode(String code) throws Exception;

  public List<String> getCommandHistory();

  public double getHeading();

  public List<Line> getLines();

  public UserVariable getVariable(String varName);

  public List<String> getAllVariables();

  public String getLanguage();

  public void setLanguage(String language);

}
