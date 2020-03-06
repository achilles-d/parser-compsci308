package slogo.model.commands;


import java.util.ArrayList;
import java.util.List;

public class For implements Command<Object> {
    private List<String> groupedCodes;
    private boolean isItExecutable;

    private List<String> index;

    private List<String> commandsToAddToStack;
    private   String RIGHT_BRACKET = "]";
    private String LEFT_BRACKET = "[";

    public For(Command index, Command group){

        this.index=(List<String>)index.execute();
        this.groupedCodes= (List<String>) group.execute();

        commandsToAddToStack=new ArrayList<>();
        parseAndRepeatTheCommand();

        isItExecutable=commandsToAddToStack.size()==0;

    }

    @Override
    public Object execute() {
        //parseAndRepeatTheCommand();
        if(isItExecutable){
            return 0.0;
        } else{
            System.out.println(" next command to execute "+commandsToAddToStack.toString());
            return commandsToAddToStack;
        }

    }

    //    for [ :dist 1 110 1 ]
//            [
//    fd :dist
//    rt product :dist 3
//            ]

    private void parseAndRepeatTheCommand(){
    //System.out.println("To repeat "+groupedCodes);
        cleanTheFirstLayerBrackets();
        int leftBracketIndex=groupedCodes.indexOf(LEFT_BRACKET);

        int righBracketIndex=groupedCodes.lastIndexOf(RIGHT_BRACKET);

        //System.out.println("Answer for the "+index);
        String variableName=index.get(1);
        int start=Integer.parseInt(index.get(2));
        int end=Integer.parseInt(index.get(3));
        int increment=Integer.parseInt(index.get(4));
        System.out.println(start);
        System.out.println(end);
        System.out.println(increment);
        //System.out.println("to repeat "+groupedCodes.toString());

        for(int i=start; i<=end; i+=increment){
            System.out.println(" Commands to repeat"+groupedCodes.toString());
            System.out.println(" Left bracket "+leftBracketIndex);
            System.out.println(" Left bracket "+righBracketIndex);
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

    @Override
    public boolean isItExecutable() {
        return isItExecutable;
    }
}
