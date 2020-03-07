package slogo.model.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MakeUserInstruction implements  Command<Object> {


    private Command command;
    private Command inputs;
    private Command name;
    private Map<String, List<List<Command>>> commandSaver;


    public  MakeUserInstruction (Command name, Command inputs, Command command,
                                 Map<String, List<List<Command>>> commandSaver){

        this.name=name;
        this.inputs=inputs;
        this.command=command;
        this.commandSaver=commandSaver;
    }

    @Override
    public Object execute() {
        List<List<Command>> cmd=new ArrayList<>();
        List<Command> values= new ArrayList<>();
        List<Command> cmds=new ArrayList<>();
        cmds.add(inputs);
        cmds.add(command);
        cmd.add(values);
        cmd.add(cmds);
        commandSaver.putIfAbsent(((List<String>) name.execute()).get(0), cmd);
        return 1.0;
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }


}
