package slogo.model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserDefined implements Command {

    private Command command;
    private Command variables;
    private String name;
    private boolean executable;
    private List<String> listOfCommands;
    private Map<String, List<List<Command>>> userDefinedCommands;

    /**
     * Command constructor
     * @param name user defined command string name
     * @param userDefinedCommands map used to map name to list of command lists
     */
    public UserDefined(String name, Map<String, List<List<Command>> > userDefinedCommands){
        this.userDefinedCommands=userDefinedCommands;
        this.name= name;
        executable=false;
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Object execute() {

        buildExecutable();
        if(executable){
            return 0.0;
        }
        return listOfCommands;
    }

    private void buildExecutable(){
        List<String> variables= new ArrayList<>();
        variables.addAll( (List<String>) userDefinedCommands.get(name).get(1).get(0).execute());
        List<String> commands=new ArrayList<>();
        commands.addAll((List<String>) userDefinedCommands.get(name).get(1).get(1).execute());
        List<Command> param= userDefinedCommands.get(name).get(0);
       removeBracket(variables);
       removeBracket(commands);


        for(int i=0; i<variables.size(); i++){

            Collections.replaceAll(commands, variables.get(i),Double.toString((Double) param.get(i).execute()));

        }
        listOfCommands=commands;
        executable=(listOfCommands.size()==0);

    }

    private void removeBracket(List<String> inputsList) {
        int size= inputsList.size();
        inputsList.remove(0);
        inputsList.remove(size-2);
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return executable;
    }
}
