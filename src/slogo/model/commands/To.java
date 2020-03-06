package slogo.model.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class To implements  Command<Object> {


    private Command command;
    private Command inputs;
    private String name;
    private Map<String, List<Command>> commandSaver;
    private boolean executable;
    private List<Command> values;
    private int state;
    private List<String> listOfCommands;


    public To (String name, Command inputs, Command command, Map<String, List<Command>> commandSaver){

        this.name=name;
        this.inputs=inputs;
        this.command=command;
        this.commandSaver=commandSaver;
        List<Command> cmd=new ArrayList<>();
        cmd.add(inputs);
        cmd.add(command);
        commandSaver.put(name,cmd);
        executable=true;
    }






    @Override
    public Object execute() {

        return 0.0;
    }

    @Override
    public boolean isItExecutable() {
        return executable;
    }


}
