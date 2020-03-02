package slogo.model.backEndInternal.commands;

import slogo.model.backEndInternal.UserVariableHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoTimes implements Command<Object> {

    private UserVariableHandler handler;
    private List<String> groupedCodes;
    private boolean isItExecutable;

    private List<String> index;
    private   String RIGHT_BRACKET = "]";
    private String LEFT_BRACKET = "[";
    List<String> repeatCommand;//new ArrayList<>();




    public DoTimes(UserVariableHandler handler,  Command repeat, Command group) {
        this.handler=handler;
        this.index=(List<String>)repeat.execute();

        this.groupedCodes= (List<String>) group.execute();
        repeatCommand=new ArrayList<>();
        parseAndRepeatTheCommand();
        isItExecutable=repeatCommand.size()==0;
    }

    @Override
    public Object execute() {
        if(isItExecutable){
            return 0.0;
        } else{
            return repeatCommand;
        }

    }

    private void parseAndRepeatTheCommand(){
        System.out.println("The repeat is "+groupedCodes.toString());

        cleanTheFirstLayerBrackets();
        System.out.println("The repeat is "+index.toString());
        repeatCommand.add("repeat");
        repeatCommand.addAll(index.subList(1,index.size()));
        for(String str: groupedCodes){

            if(str.equals(index.get(0))){
                repeatCommand.add(":repcount");
            } else{
                repeatCommand.add(str);
            }
            System.out.println("The repeat is "+str);

        }
System.out.println("next command is "+repeatCommand.toString());
    }

    private void cleanTheFirstLayerBrackets() {
        if(this.index.get(0).equals(LEFT_BRACKET)){ this.index.remove(0);}
        if(this.index.get(this.index.size()-1).equals(RIGHT_BRACKET)){
            this.index.remove(this.index.size()-1);}
    }

    @Override
    public boolean isItExecutable() {
        return isItExecutable;
    }
}
