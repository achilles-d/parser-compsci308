package slogo.model.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MakeUserInstruction implements  Command<Object> {


    private Command command;
    private Command inputs;
    private Command name;
    private Map<String, List<List<Command>>> commandSaver;
    private boolean executable;
    private List<Command> values;
    private int state;
    private List<String> listOfCommands;


    public  MakeUserInstruction (Command name, Command inputs, Command command,
                                 Map<String, List<List<Command>>> commandSaver){

        this.name=name;
        this.inputs=inputs;
        this.command=command;
        this.commandSaver=commandSaver;
        //executable=true;
        System.out.println("Reching to the To");
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
        System.out.println("name of "+ name.execute());
        //System.out.println(((List<String>) name.execute()).get(0));
        commandSaver.putIfAbsent(((List<String>) name.execute()).get(0), cmd);
        System.out.println(commandSaver.get("test"));
        return 1.0;
    }

    @Override
    public boolean isItExecutable() {
        return true;
    }


}
