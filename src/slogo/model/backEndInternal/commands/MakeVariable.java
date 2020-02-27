package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.UserVariableHandler;

import java.util.List;

public class MakeVariable<T> implements Command<Double> {

  private UserVariableHandler<T> myHandler;
  private String variableName;

  public MakeVariable(UserVariableHandler handler, String name, Double userVal) {
    this.variableName = name;
    this.myHandler = handler;
    handler.makeVariable(name, userVal);
  }

  @Override
  public Double execute() {
    return myHandler.getVariable(variableName).getValue();
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
