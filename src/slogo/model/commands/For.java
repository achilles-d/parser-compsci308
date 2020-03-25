package slogo.model.commands;


import java.util.ArrayList;
import java.util.List;

public class For implements Command<Object> {
    private List<String> groupedCodes;
    private boolean isItExecutable;

    private List<String> index;

    private List<String> commandsToAddToStack;
    private String RIGHT_BRACKET = "]";
    private String LEFT_BRACKET = "[";
    private Command indexCmd;
    private Command group;

    /**
     * Command constructor
     * @param index loop index
     * @param group group of commands
     */
    public For(Command index, Command group){
       this.group=group;
       this.indexCmd=index;
       commandsToAddToStack=new ArrayList<>();
    }

    /**
     * Execution logic
     * @return Double value argument
     */
    @Override
    public Object execute() {
        //();
        this.index=(List<String>)indexCmd.execute();
        this.groupedCodes= (List<String>) group.execute();
        parseAndRepeatTheCommand();
        isItExecutable=commandsToAddToStack.size()==0;
        if(isItExecutable){
            return 0.0;
        } else{
            return commandsToAddToStack;
        }

    }


    private void parseAndRepeatTheCommand(){
        cleanTheFirstLayerBrackets();
        int leftBracketIndex=groupedCodes.indexOf(LEFT_BRACKET);

        int righBracketIndex=groupedCodes.lastIndexOf(RIGHT_BRACKET);
        String variableName=index.get(1);
        int start=Integer.parseInt(index.get(2));
        int end=Integer.parseInt(index.get(3));
        int increment=Integer.parseInt(index.get(4));
        for(int i=start; i<=end; i+=increment){
            for(String str:groupedCodes){
                if(leftBracketIndex<=i && i<= righBracketIndex){
                    commandsToAddToStack.add(str);
                    continue;
                }
                if(str.equals(variableName)){
                    commandsToAddToStack.add(Integer.toString(i));
                } else{
                    commandsToAddToStack.add(str);
                }

            }

        }

    }

    private void cleanTheFirstLayerBrackets() {
        groupedCodes.remove(0);
        groupedCodes.remove(groupedCodes.size()-1);
    }

    /**
     * Check if executable
     * @return Is it an executable command or not.
     */
    @Override
    public boolean isItExecutable() {
        return isItExecutable;
    }
}
