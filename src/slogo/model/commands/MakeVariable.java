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

      Object nam=nameCmd.execute();
    Class<?> result=nam.getClass();
    String className = (((Class) result).getName().split("[.]"))[result.getName().split("[.]").length - 1];

   if (className.equals("String") && myHandler.getKeys().contains(nam)) {
        Double value = (Double) valueCmd.execute();
        myHandler.setVariable((String) nameCmd.execute(), value);
        return value;

   } else if(className.equals("String") && !myHandler.getKeys().contains(nam)){
          Double value = (Double) valueCmd.execute();
          myHandler.makeVariable((String) nam, value);
          return value;
      } else{
       return 0.0;
   }

  }

  @Override
  public boolean isItExecutable() {
    return true;
  }
}
