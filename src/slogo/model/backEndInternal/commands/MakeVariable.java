package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.UserVariableHandler;

import java.util.List;

public class MakeVariable<T> implements Command<Double> {

  private UserVariableHandler<T> myHandler;
  private Command nameCmd;
  private Command valueCmd;

  public MakeVariable(UserVariableHandler handler, Command nameCmd, Command valueCmd) {
    this.nameCmd=nameCmd;
    this.valueCmd=valueCmd;
    this.myHandler = handler;
  }

  @Override
  public Double execute() {

    String variableName = (String) nameCmd.execute();
    Double value = (Double) valueCmd.execute();

    myHandler.makeVariable(variableName, value);

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
