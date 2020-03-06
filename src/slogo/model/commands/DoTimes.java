package slogo.model.commands;

import slogo.controller.Language;
import slogo.model.turtle.UserVariableHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DoTimes implements Command<Object> {

    private UserVariableHandler handler;
    private List<String> groupedCodes;
    private boolean isItExecutable;

    private List<String> index;
    private static final String RIGHT_BRACKET = "]";
    private static final String LEFT_BRACKET = "[";
    private static final String  REPCOUNT=":repcount";
    private List<String> repeatCommand;
    private Command repeat;
    private Command group;
    private Language lan;//=new Re

    public DoTimes(UserVariableHandler handler, Language language, Command repeat, Command group) {
        this.handler=handler;
        this.group=group;
        this.repeat=repeat;
        isItExecutable=false;
        this.lan=language;
        System.out.println("do times is reached");
    }

    @Override
    public Object execute() {

        index=(List<String>)repeat.execute();

        groupedCodes= (List<String>) group.execute();
        repeatCommand=new ArrayList<>();
        parseAndRepeatTheCommand();
        isItExecutable=repeatCommand.size()==0;

        if(isItExecutable){
            return 0.0;
        } else{
            return repeatCommand;
        }

    }

    private void parseAndRepeatTheCommand(){
        cleanTheFirstLayerBrackets();
        repeatCommand.add("repeat");

        repeatCommand.addAll(index.subList(1,index.size()));
        for(String str: groupedCodes){

            if(str.equals(index.get(0))){
                repeatCommand.add(REPCOUNT);
            } else{
                repeatCommand.add(str);
            }

        }
    }

    private void cleanTheFirstLayerBrackets() {
        if(this.index.get(0).equals(LEFT_BRACKET)){ this.index.remove(0);}
        if(this.index.get(this.index.size()-1).equals(RIGHT_BRACKET)){
            this.index.remove(this.index.size()-1);}
    }

    private String findRepeatCommand(){
        ResourceBundle rs=ResourceBundle.getBundle(lan.getLanguageFile());
       return rs.getString("Repeat");
       // return ;
    }

    @Override
    public boolean isItExecutable() {
        return isItExecutable;
    }
}
