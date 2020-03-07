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

    public UserDefined(String name, Map<String, List<List<Command>> > userDefinedCommands){
        this.userDefinedCommands=userDefinedCommands;
        this.name= name;
        System.out.println("User Defined reached ");
        executable=false;
    }

    @Override
    public Object execute() {

        buildExecutable();
        if(executable){
            return 0.0;
        }
        return listOfCommands;
    }


    private void buildExecutable(){
        //variables=new ArrayList<>();
        List<String> variables= new ArrayList<>();
        variables.addAll( (List<String>) userDefinedCommands.get(name).get(1).get(0).execute());

       // List<String> variables= (List<String>) userDefinedCommands.get(name).get(1).get(0).execute();
        List<String> commands=new ArrayList<>();
        commands.addAll((List<String>) userDefinedCommands.get(name).get(1).get(1).execute());

        //List<String> commands=(List<String>) userDefinedCommands.get(name).get(1).get(1).execute();
        List<Command> param= userDefinedCommands.get(name).get(0);
       removeBracket(variables);
       removeBracket(commands);


        for(int i=0; i<variables.size(); i++){

            Collections.replaceAll(commands, variables.get(i),Double.toString((Double) param.get(i).execute()));

        }
        listOfCommands=commands;
        System.out.println(" commmands "+listOfCommands);
        executable=(listOfCommands.size()==0);

    }

    private void removeBracket(List<String> inputsList) {
        System.out.println(" before cut "+inputsList.toString());
        int size= inputsList.size();
        System.out.println(inputsList.get(0));
        System.out.println(inputsList.get(size-1));

        inputsList.remove(0);
        System.out.println(" after first cut "+inputsList.toString());
        size= inputsList.size();
        inputsList.remove(size-1);
        System.out.println(" after second cut "+inputsList.toString());
    }

    @Override
    public boolean isItExecutable() {
        return executable;
    }
}
