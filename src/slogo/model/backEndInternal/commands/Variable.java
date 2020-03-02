package slogo.model.backEndInternal.commands;

import slogo.model.InvalidCommandException;
import slogo.model.backEndInternal.UserVariableHandler;

import java.util.List;

public class Variable implements Command {


    private String name;
    private UserVariableHandler handler;

    public Variable(UserVariableHandler handler, String variableName)
    {
        this.handler=handler;
        this.name=variableName;
    }

    @Override
    public Object execute() {
        if(handler.getKeys().contains(name)){
            //handler.getVariable(name).
            return (double)handler.getVariable(name).getValue();

        } else{
            return (String) name;
        }
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
