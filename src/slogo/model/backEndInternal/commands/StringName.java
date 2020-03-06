package slogo.model.backEndInternal.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringName implements Command {


    private Command command;
    private Command inputs;
    private String name;
    private Map<String, List<Command>> commandSaver;
    private boolean executable;
    private List<Command> values;
    private int state;
    private List<String> listOfCommands;

    public StringName(String name){
        System.out.println("Name initialized " +name);
        executable=false;
        this.name=name;
        executable=false;
        state=0;
    }
    @Override
    public String execute() {
        List<String> nameList=new ArrayList<>();
        nameList.add(name);
        return name;
    }

    @Override
    public boolean isItExecutable() {
        return false;
    }
}
