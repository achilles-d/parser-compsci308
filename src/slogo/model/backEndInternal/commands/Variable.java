package slogo.model.backEndInternal.commands;

import java.util.List;

public class Variable implements Command {


    private String name;
    public Variable(String variableName){
        this.name=variableName;
    }

    @Override
    public Object execute() {
        return name;
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
