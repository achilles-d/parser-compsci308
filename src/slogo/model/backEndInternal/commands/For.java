package slogo.model.backEndInternal.commands;

import java.util.ArrayList;
import java.util.List;

public class For implements Command<Double> {

    private int commandCounter;
    private int unexecutedCounter;
    private List<String> commandList;
    private String LEFT_BRACKET = "[";
    private String RIGHT_BRACKET = "]";
    private int start;
    private int end;
    private int increment;
    private String vairableName;
    private int numberOfLeft=0;
    private int numberOfRight=0;
    private int forLoopRun;
    private List<String> commandToRepeat;
    private List<String> parsedCommand;

    public For(List<String> commandList, Integer counter){
        this.commandCounter=counter;
        this.commandList=commandList;
        this.unexecutedCounter=commandList.size();
        commandToRepeat=new ArrayList<>();
        parsedCommand=new ArrayList<>();


    }


    @Override
    public Double execute() {
        System.out.println("cCounte initiallly "+ commandCounter);
        //commandCounter=0;
        if(commandList.get(commandCounter+1).equals(LEFT_BRACKET)){
            commandCounter+=2;
            System.out.println("Elements in the list "+ commandList.get(commandCounter));
            vairableName=commandList.get(commandCounter);
            start=Integer.parseInt(commandList.get(commandCounter+1));
            System.out.println("Start fo the " +start);
            end=Integer.parseInt(commandList.get(commandCounter+2));
            System.out.println("Start fo the " +end);
            increment=Integer.parseInt((commandList.get(commandCounter+3)));
            System.out.println("Start fo the " +increment);
            commandCounter+=5; // ends skip the closing bracket
            System.out.println("Elements in the list "+ commandList.get(commandCounter));
        }

       getTheCommandToRepeat();
        System.out.println("For, command to repeat "+commandToRepeat);
        parseAndRepeatTheCommand();
        parsedCommand.addAll( commandList.subList(commandCounter, unexecutedCounter));
        if(parsedCommand!=null){
            return Double.MAX_VALUE;
        } else{
            return 0.0;
        }

    }

    private void getTheCommandToRepeat() {
        //commandToRepeat = new ArrayList<>();
        if (commandList.get(commandCounter).equals(LEFT_BRACKET)) {
            commandCounter += 1;
            numberOfLeft += 1;
            while (numberOfLeft > numberOfRight && commandCounter<unexecutedCounter) {

                    System.out.println("Command to add "+ commandList.get(commandCounter));
                    if(commandList.get(commandCounter).equals(LEFT_BRACKET)){
                        numberOfLeft+=1;
                    } else if(commandList.get(commandCounter).equals(RIGHT_BRACKET)){
                        numberOfRight+=1;
                    }
                    if(numberOfRight!=numberOfLeft) {
                        commandToRepeat.add(commandList.get(commandCounter));
                    }

                    commandCounter+=1;
            }
        }
    }


    private void parseAndRepeatTheCommand(){

        for(int i=start; i<=end; i+=increment){

            for(String str: commandToRepeat){
                System.out.println("Command to repeat is "+str);
                if(str.equals(vairableName)){
                    parsedCommand.add(i+"");
                } else {
                    parsedCommand.add(str);
                }
            }

        }



    }

    @Override
    public boolean isItExecutable() {
        return true;
    }
}
