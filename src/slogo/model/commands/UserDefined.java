package slogo.model.commands;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserDefined implements Command {

    private Command command;
    private Command inputs;
    private Map<String, List<Command>> commandSaver;
    private boolean executable;
    private List<Command> values;
    private int state;
    private List<String> listOfCommands;

    public UserDefined(List<Object> param){
        System.out.println(" UserDefined constructor reached 2 " +param.size());
        //List<Command> values, Command inputs,Command command
        this.values=(List<Command>) param.get(0);
        this.inputs= (Command) param.get(1);
        this.command= (Command) param.get(2);
        state=1;
    }

    @Override
    public Object execute() {

        buildExecutable();
        executable=listOfCommands.size()==0;
        if(executable){
            return 0.0;
        }
        return listOfCommands;
    }

    private void buildExecutable(){

        List<String> inputsList= (List<String>) inputs.execute();
        removeBracket(inputsList);
        listOfCommands= (List<String>) command.execute();
        removeBracket((listOfCommands));

        for(int i=0; i<inputsList.size(); i++){

            Collections.replaceAll(listOfCommands, inputsList.get(i),(String)values.get(i).execute());

        }
    }

    private void removeBracket(List<String> inputsList) {
        inputsList.remove(0);
        inputsList.remove(inputsList.size()-1);
    }

    @Override
    public boolean isItExecutable() {
        return executable;
    }
}
