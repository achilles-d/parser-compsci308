package slogo.model.commands;

import slogo.model.exceptions.InvalidCommandException;
import slogo.model.turtle.UserVariableHandler;

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

    Class<?> result=nameCmd.execute().getClass();
    String className = (((Class) result).getName().split("[.]"))[result.getName().split("[.]").length - 1];

    if(className.equals("Double")){
     System.out.println("Answer should stop here");
      throw new InvalidCommandException("");

    } else{
     System.out.println("Type is "+className);
     String variableName = (String) nameCmd.execute();
     Double value = (Double) valueCmd.execute();

    if(!myHandler.getKeys().contains(variableName)){
        myHandler.makeVariable(variableName, value);

    }
     return myHandler.getVariable(variableName).getValue();
   }

  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
