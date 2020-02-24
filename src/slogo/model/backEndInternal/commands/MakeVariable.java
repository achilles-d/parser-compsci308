package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.UserVariable;

public class MakeVariable<T> implements Command<UserVariable<T>> {

  UserVariable<T> newVar;

  public MakeVariable(T userVal) {
    this.newVar = new UserVariable<>();
    newVar.setValue(userVal);
  }

  @Override
  public UserVariable<T> execute() {
    return newVar;
  }
}
