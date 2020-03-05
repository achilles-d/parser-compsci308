package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.UserVariableHandler;


public class Variable implements Command {

    private boolean isItExecutable;
    private String name;
    private UserVariableHandler handler;

    public Variable(UserVariableHandler handler, String variableName)
    {
        this.handler=handler;
        this.name=variableName;
        isItExecutable=handler.getKeys().contains(name);

    }

    @Override
    public Object execute() {
        if(handler.getKeys().contains(name)){
            return (double)handler.getVariable(name).getValue();

        } else{
            return (String) name;
        }
    }

    @Override
    public boolean isItExecutable() {
        return isItExecutable;
    }

}
