package slogo.model.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class To implements  Command<Object> {


    private Command command;
    private Command inputs;
    private Command name;
    private Map<String, List<Command>> commandSaver;
    private boolean executable;
    private List<Command> values;
    private int state;
    private List<String> listOfCommands;


    public To (Command name, Command inputs, Command command, Map<String, List<Command>> commandSaver){

        this.name=name;
        this.inputs=inputs;
        this.command=command;
        this.commandSaver=commandSaver;
        executable=true;
        System.out.println("Reching to the To");
    }

    @Override
    public Object execute() {
        List<Command> cmd=new ArrayList<>();
        cmd.add(inputs);
        cmd.add(command);
        commandSaver.put((String) name.execute(),cmd);
        return 1.0;
    }

    @Override
    public boolean isItExecutable() {
        return executable;
    }


}
