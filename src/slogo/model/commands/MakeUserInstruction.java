package slogo.model.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MakeUserInstruction implements  Command<Object> {


    private Command command;
    private Command inputs;
    private String name;
    private Map<String, List<Command>> commandSaver;
    private boolean executable;
    private List<Command> values;
    private int state;
    private List<String> listOfCommands;


    public MakeUserInstruction (Command nameOfCommand, Command inputs, Command command,
                                Map<String, List<Command>> commandSaver){

        this.name= (String) nameOfCommand.execute();
        this.inputs=inputs;
        this.command=command;
        this.commandSaver=commandSaver;

        putCommandToTheMap();

        //System.out.println("Added command to commandSaver "+name);
        executable=true;
    }

    private void putCommandToTheMap() {
        List<Command> cmd=new ArrayList<>();

        cmd.add(inputs);
        cmd.add(command);
        commandSaver.put(name,cmd);
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
