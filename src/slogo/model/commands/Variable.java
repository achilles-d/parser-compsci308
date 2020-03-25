package slogo.model.commands;

import slogo.model.turtle.UserVariableHandler;


public class Variable implements Command {

    private boolean isItExecutable;
    private String name;
    private UserVariableHandler handler;

    /**
     * command constructor
     * @param handler user variable handler with info
     * @param variableName name of variable
     */
    public Variable(UserVariableHandler handler, String variableName)
    {
        this.handler=handler;
        this.name=variableName;
        isItExecutable=handler.getKeys().contains(name);

    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Object execute() {
        if(handler.getKeys().contains(name)){
            return (double)handler.getVariable(name).getValue();

        } else{
            return (String) name;
        }
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return isItExecutable;
    }

}
